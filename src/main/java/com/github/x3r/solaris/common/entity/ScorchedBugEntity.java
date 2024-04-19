package com.github.x3r.solaris.common.entity;

import com.github.x3r.solaris.common.registry.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import net.tslat.smartbrainlib.example.SBLSkeleton;
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
        boolean b = super.hurt(pSource, inBall ? pAmount * 0.5F : pAmount);
        if(isDeadOrDying() && inBall) {
            triggerAnim("controller", "exit_ball");
            inBall = false;
        }
        return b;
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
    public List<? extends ExtendedSensor<? extends ScorchedBugEntity>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<ScorchedBugEntity>()
                        .setRadius(4)
                        .setScanRate(scorchedBugEntity -> 17+Math.round(3*this.random.nextFloat()))
        );
    }

    @Override
    public BrainActivityGroup<? extends ScorchedBugEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<ScorchedBugEntity>()
                        .stopIf(scorchedBugEntity -> scorchedBugEntity.inBall),
                new MoveToWalkTarget<ScorchedBugEntity>()
                        .stopIf(scorchedBugEntity -> scorchedBugEntity.inBall)
        );
    }

    @Override
    public BrainActivityGroup<ScorchedBugEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new SetPlayerLookTarget<ScorchedBugEntity>()
                                .predicate(player -> player.distanceToSqr(this) < 40),
                        new SetRandomLookTarget<ScorchedBugEntity>()
                                .stopIf(scorchedBugEntity -> scorchedBugEntity.inBall)
                ),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<ScorchedBugEntity>()
                                .setRadius(6)
                                .speedModifier(1.3F)
                                .stopIf(scorchedBugEntity -> scorchedBugEntity.inBall),
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
