package com.github.x3r.solaris.common.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
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
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class UrborosEphyraEntity extends Monster implements SmartBrainOwner<UrborosEphyraEntity>, GeoEntity, RangedAttackMob {

    public static final EntityDataAccessor<Byte> ATTACK_STATE = SynchedEntityData.defineId(UrborosEphyraEntity.class, EntityDataSerializers.BYTE);

    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation BITE = RawAnimation.begin().thenPlay("bite");

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public UrborosEphyraEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, 2F)
                .add(Attributes.ATTACK_DAMAGE, 30.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 1.1F)
                .add(Attributes.MAX_HEALTH, 8.0F)
                .add(Attributes.FLYING_SPEED, 0.5F)
//                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .build();
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
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
    public List<? extends ExtendedSensor<? extends UrborosEphyraEntity>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEphyraEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<UrborosEphyraEntity>()
                        .startCondition(urborosEphyraEntity ->
                                urborosEphyraEntity.getTarget() != null &&
                                        !urborosEphyraEntity.getTarget().isDeadOrDying() &&
                                        !urborosEphyraEntity.isDeadOrDying()
                        )
                        .stopIf(urborosEphyraEntity ->
                                urborosEphyraEntity.getTarget() == null ||
                                        urborosEphyraEntity.getTarget().isDeadOrDying() ||
                                        urborosEphyraEntity.isDeadOrDying()
                        )
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEphyraEntity> getIdleTasks() {
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
                        new SetRandomFlyingTarget<UrborosEphyraEntity>()
                                .setRadius(5)
                                .runFor(pathfinderMob -> pathfinderMob.getRandom().nextInt(40, 80)),
                        new Idle<UrborosEphyraEntity>()
                                .runFor(livingEntity -> livingEntity.getRandom().nextInt(40, 60))
                )
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEphyraEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget(),
                new SetWalkTargetToAttackTarget<UrborosEphyraEntity>(),
                new OneRandomBehaviour<>(
                        new AnimatableMeleeAttack<UrborosEphyraEntity>(4) {
                            @Override
                            protected void start(UrborosEphyraEntity entity) {
                                BehaviorUtils.lookAtEntity(entity, this.target);
                                entity.triggerAnim("controller", "bite");
                            }
                        }
                                .attackInterval(entity -> 40)
                                .runFor(entity -> 40)
                                .whenStarting(entity -> entity.setAttackState((byte) 1))
                                .whenStopping(entity -> entity.setAttackState((byte) 0))
                                .cooldownFor(entity -> entity.random.nextInt(20, 30))
                )
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 1, this::controller)
                .triggerableAnim("bite", BITE));
    }

    protected <E extends UrborosEphyraEntity> PlayState controller(final AnimationState<E> event) {
        return event.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
