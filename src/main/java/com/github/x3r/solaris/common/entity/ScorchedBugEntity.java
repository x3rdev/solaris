package com.github.x3r.solaris.common.entity;

import com.github.x3r.solaris.common.registry.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.data.SoundDefinition;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class ScorchedBugEntity extends PathfinderMob implements SmartBrainOwner<ScorchedBugEntity>, GeoEntity {

    protected static final RawAnimation ENTER_BALL = RawAnimation.begin().thenPlayAndHold("enter_ball");
    protected static final RawAnimation EXIT_BALL = RawAnimation.begin().thenPlay("exit_ball");
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    protected static final RawAnimation SCURRY = RawAnimation.begin().thenLoop("scurry");

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private boolean inBall = false;

    public ScorchedBugEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .build();
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        tickBrain(this);
        if (getBrain().checkMemory(MemoryModuleType.NEAREST_PLAYERS, MemoryStatus.VALUE_ABSENT) || getBrain().getMemory(MemoryModuleType.NEAREST_PLAYERS).get().isEmpty()) {
            if(inBall) {
                triggerAnim("controller", "exit_ball");
                this.playSound(SoundRegistry.SCORCHED_BUG_EXIT_BALL.get());
                inBall = false;
            }
        } else {
            if(!inBall) {
                triggerAnim("controller", "enter_ball");
                this.playSound(SoundRegistry.SCORCHED_BUG_ENTER_BALL.get());
                inBall = true;
            }
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return super.hurt(pSource, inBall ? pAmount * 0.5F : pAmount);
    }

    @Override
    public void knockback(double pStrength, double pX, double pZ) {
        super.knockback(inBall ? pStrength * 0.5F : pStrength, pX, pZ);
    }

    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public List<ExtendedSensor<ScorchedBugEntity>> getSensors() {
        NearbyPlayersSensor<ScorchedBugEntity> s1 = new NearbyPlayersSensor<>();
        s1.setScanRate(scorchedBugEntity -> 17+Math.round(3*this.random.nextFloat()));
        s1.setRadius(4);
        return List.of(
                s1
        );
    }

    @Override
    public BrainActivityGroup<? extends ScorchedBugEntity> getCoreTasks() {
        LookAtTarget<ScorchedBugEntity> a1 = new LookAtTarget<>();
        a1.stopIf(scorchedBugEntity -> scorchedBugEntity.inBall);
        MoveToWalkTarget<ScorchedBugEntity> a2 = new MoveToWalkTarget<>();
        a2.stopIf(scorchedBugEntity -> scorchedBugEntity.inBall);
        return BrainActivityGroup.coreTasks(
                a1,
                a2
        );
    }

    @Override
    public BrainActivityGroup<ScorchedBugEntity> getIdleTasks() {
        SetPlayerLookTarget<ScorchedBugEntity> a1 = new SetPlayerLookTarget<>();
        a1.predicate(player -> player.distanceToSqr(this) < 40);
        SetRandomWalkTarget<ScorchedBugEntity> a3 = new SetRandomWalkTarget<>();
        a3.setRadius(6);
        a3.speedModifier(1.3F);
        a3.stopIf(scorchedBugEntity -> scorchedBugEntity.inBall);
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        a1,
                        new SetRandomLookTarget<>()
                ),
                new OneRandomBehaviour<>(
                        a3,
                        new Idle<>().runFor(livingEntity -> livingEntity.getRandom().nextInt(30, 60))
                )
        );
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundRegistry.SCORCHED_BUG_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.SCORCHED_BUG_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        if(!this.inBall) {
            this.playSound(SoundRegistry.SCORCHED_BUG_STEP.get(), 0.4F, 1.0F);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 1, this::controller)
                .triggerableAnim("enter_ball", ENTER_BALL)
                .triggerableAnim("exit_ball", EXIT_BALL));
    }

    protected <E extends ScorchedBugEntity> PlayState controller(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(SCURRY);
        }
        return event.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
