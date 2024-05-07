package com.github.x3r.solaris.common.entity;

import com.github.x3r.solaris.common.entity.brain.UrborosMoveControl;
import com.github.x3r.solaris.common.helper.ParticleHelper;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class UrborosEntity extends Monster implements SmartBrainOwner<UrborosEntity>, GeoEntity, RangedAttackMob {

    public static final EntityDataAccessor<Byte> ATTACK_STATE = SynchedEntityData.defineId(SnowTrollEntity.class, EntityDataSerializers.BYTE);

    protected static final RawAnimation TENTACLES = RawAnimation.begin().thenLoop("tentacles");
    protected static final RawAnimation JAW_IDLE = RawAnimation.begin().thenLoop("jaw_idle");
    protected static final RawAnimation EYE = RawAnimation.begin().thenPlay("eye");
    protected static final RawAnimation FLY = RawAnimation.begin().thenPlay("fly");
    protected static final RawAnimation BITE_1 = RawAnimation.begin().thenPlay("bite1");
    protected static final RawAnimation BITE_2 = RawAnimation.begin().thenPlay("bite2");
    protected static final RawAnimation FIREBALL = RawAnimation.begin().thenPlay("fireball");
    protected static final RawAnimation EMPTY = RawAnimation.begin().thenPlay("empty");

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public UrborosEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new UrborosMoveControl(this);
        this.setNoGravity(true);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 8.0F)
                .add(Attributes.MAX_HEALTH, 25.0F)
                .add(Attributes.FLYING_SPEED, 0.75F)
                .build();
    }

    @Override
    public double getMeleeAttackRangeSqr(LivingEntity pEntity) {
        return Mth.square(4.5F);
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new FlyingPathNavigation(this, pLevel);
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {

    }

    public void setAttackState(byte state) {
        this.entityData.set(ATTACK_STATE, state);
    }

    public byte getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, (byte)0);
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        Vec3 vec = getLookAngle().normalize().scale(2);
        SmallFireball fireball = new SmallFireball(level(), this, vec.x, vec.y+1.5, vec.z);
        fireball.setDeltaMovement(position().vectorTo(pTarget.position()).normalize().scale(0.5));
        level().addFreshEntity(fireball);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public int getMaxHeadYRot() {
        return 0;
    }

    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends UrborosEntity>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<UrborosEntity>()
                        .startCondition(urborosEntity ->
                                urborosEntity.getTarget() != null &&
                                        !urborosEntity.getTarget().isDeadOrDying() &&
                                        !urborosEntity.isDeadOrDying()
                        )
                        .stopIf(urborosEntity ->
                                urborosEntity.getTarget() == null ||
                                        urborosEntity.getTarget().isDeadOrDying() ||
                                        urborosEntity.isDeadOrDying()
                        )
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new TargetOrRetaliate<>()
                                .attackablePredicate(livingEntity ->
                                        livingEntity instanceof Player ||
                                                livingEntity instanceof ZombifiedPiglin),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()
                ),
                new OneRandomBehaviour<>(
                        new SetRandomFlyingTarget<UrborosEntity>()
                                .setRadius(7),
                        new Idle<UrborosEntity>()
                                .runFor(livingEntity -> livingEntity.getRandom().nextInt(40, 60)),
                        new Idle<UrborosEntity>()
                                .whenStarting(urborosEntity -> urborosEntity.triggerAnim("controller", "eye"))
                                .runFor(livingEntity -> 220)
                                .cooldownFor(livingEntity -> 100)
                )
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<UrborosEntity>(),
                new OneRandomBehaviour<>(
                        new AnimatableMeleeAttack<UrborosEntity>(4) {
                            @Override
                            protected void start(UrborosEntity entity) {
                                entity.lookAt(EntityAnchorArgument.Anchor.EYES, entity.getTarget().position());
                                entity.triggerAnim("jaw_controller", "bite_"+entity.random.nextInt(3));
                            }
                        }
                                .startCondition(urborosEntity -> urborosEntity.getAttackState() == 0)
                                .runFor(entity -> 30)
                                .whenStarting(entity -> entity.setAttackState((byte) 1))
                                .whenStopping(entity -> entity.setAttackState((byte) 0))
                                .cooldownFor(entity -> 25)),
                        new AnimatableRangedAttack<UrborosEntity>(10) {
                            @Override
                            protected void start(UrborosEntity entity) {
                                entity.lookAt(EntityAnchorArgument.Anchor.EYES, entity.getTarget().position());
                                entity.triggerAnim("jaw_controller", "fire_breath");
                            }
                        }
                                .startCondition(urborosEntity -> urborosEntity.getAttackState() == 0)
                                .runFor(entity -> 40)
                                .whenStarting(entity -> entity.setAttackState((byte) 2))
                                .whenStopping(entity -> {
                                    entity.setAttackState((byte) 0);
                                    entity.triggerAnim("jaw_controller", "empty");
                                })
                                .cooldownFor(entity -> 120)
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "jaw_controller", 1, state -> state.setAndContinue(JAW_IDLE))
                .triggerableAnim("bite_1", BITE_1)
                .triggerableAnim("bite_2", BITE_2)
                .triggerableAnim("fireball", FIREBALL)
                .triggerableAnim("empty", EMPTY));
        controllers.add(new AnimationController<>(this, "tentacle_controller", 1, state -> state.setAndContinue(TENTACLES)));
        controllers.add(new AnimationController<>(this, "eye_controller", 1, state -> PlayState.CONTINUE)
                .triggerableAnim("eye", EYE));
        controllers.add(new AnimationController<>(this, "fly_controller", 1, state -> state.setAndContinue(FLY)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
