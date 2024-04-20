package com.github.x3r.solaris.common.entity;

import com.github.x3r.solaris.common.datagen.SolarisBlockTagsProvider;
import com.github.x3r.solaris.common.entity.brain.ThrowBlockAttack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
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
import java.util.Optional;

public class SnowTrollEntity extends Monster implements SmartBrainOwner<SnowTrollEntity>, GeoEntity, RangedAttackMob {

    public static final EntityDataAccessor<Byte> ATTACK_STATE = SynchedEntityData.defineId(SnowTrollEntity.class, EntityDataSerializers.BYTE);

    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
    protected static final RawAnimation RUN = RawAnimation.begin().thenPlay("run");
    public static final RawAnimation SNIFF = RawAnimation.begin().thenPlay("sniff");
    protected static final RawAnimation SLAM = RawAnimation.begin().thenPlay("slam");
    protected static final RawAnimation THROW = RawAnimation.begin().thenPlay("throw");
    public static final RawAnimation DEATH = RawAnimation.begin().thenPlay("death");

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SnowTrollEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, 12F)
                .add(Attributes.ATTACK_DAMAGE, 30.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 1.1F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F)
                .add(Attributes.MAX_HEALTH, 70.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
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
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean b = super.hurt(pSource, pAmount);
        if(isDeadOrDying()) {
            triggerAnim("controller", "death");
        }
        return b;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime >= 96 && !this.level().isClientSide() && !this.isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        Vec3 pickupOffset = position().add(new Vec3(-1, -1, -1).yRot((float) Math.toRadians(180 - getYRot())));
        BlockPos pos1 = new BlockPos((int) pickupOffset.x, (int) pickupOffset.y, (int) pickupOffset.z);
        if(level().getBlockState(pos1).is(SolarisBlockTagsProvider.SNOW_TROLL_THROWABLE)) {
            Vec3 throwOffset = position().add(new Vec3(0.75, 2, -0.75).yRot((float) Math.toRadians(180 - getYRot())));
            BlockPos pos2 = new BlockPos((int) throwOffset.x, (int) throwOffset.y, (int) throwOffset.z);
            ThrownBlockEntity fallingBlock = new ThrownBlockEntity(level().getBlockState(pos1), pos2.getX() + 0.5, pos2.getY(), pos2.getZ() + 0.5, level());
            fallingBlock.setOwner(this);
            fallingBlock.setDeltaMovement(new Vec3(pos2.getX() + 0.5, pos2.getY(), pos2.getZ() + 0.5).vectorTo(pTarget.position()).normalize().scale(1.25));
            level().addFreshEntity(fallingBlock);
        }
    }

    @Override
    public int getMaxHeadYRot() {
        return 50;
    }

    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends SnowTrollEntity>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends SnowTrollEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<SnowTrollEntity>()
                        .startCondition(snowTrollEntity ->
                                        snowTrollEntity.getAttackState() == 0 &&
                                                (
                                                snowTrollEntity.getTarget() != null &&
                                                        !snowTrollEntity.getTarget().isDeadOrDying() &&
                                                                !snowTrollEntity.isDeadOrDying()
                                                )
                        )
                        .stopIf(snowTrollEntity ->
                                        snowTrollEntity.getAttackState() != 0 ||
                                                (
                                                        snowTrollEntity.getTarget() == null ||
                                                                snowTrollEntity.getTarget().isDeadOrDying() ||
                                                                        snowTrollEntity.isDeadOrDying()
                                                )
                        )
        );
    }

    @Override
    public BrainActivityGroup<? extends SnowTrollEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new TargetOrRetaliate<>()
                                .attackablePredicate(livingEntity ->
                                        livingEntity instanceof Player ||
                                        livingEntity instanceof IronGolem ||
                                        livingEntity instanceof SnowGolem ||
                                        livingEntity instanceof Fox ||
                                        livingEntity instanceof PolarBear),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()
                ),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<SnowTrollEntity>().setRadius(10)
                                .runFor(pathfinderMob -> pathfinderMob.getRandom().nextInt(40, 100)),
                        new Idle<SnowTrollEntity>()
                                .runFor(livingEntity -> livingEntity.getRandom().nextInt(60, 80)),
                        new Idle<SnowTrollEntity>()
                                .startCondition(snowTrollEntity -> snowTrollEntity.getDeltaMovement().lengthSqr() < 0.1)
                                .runFor(livingEntity -> 85)
                                .whenStarting(snowTrollEntity -> snowTrollEntity.triggerAnim("controller", "sniff"))
                                .cooldownFor(snowTrollEntity -> snowTrollEntity.random.nextInt(100, 200))
                )
        );
    }

    @Override
    public BrainActivityGroup<? extends SnowTrollEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<SnowTrollEntity>(),
                new OneRandomBehaviour<>(
                        new AnimatableMeleeAttack<SnowTrollEntity>(14) {
                            @Override
                            protected void start(SnowTrollEntity entity) {
                                BehaviorUtils.lookAtEntity(entity, this.target);
                                entity.triggerAnim("controller", "slam");
                            }
                        }
                                .attackInterval(snowTrollEntity -> 40)
                                .runFor(snowTrollEntity -> 40)
                                .whenStarting(snowTrollEntity -> snowTrollEntity.setAttackState((byte) 1))
                                .whenStopping(snowTrollEntity -> snowTrollEntity.setAttackState((byte) 0))
                                .cooldownFor(snowTrollEntity -> snowTrollEntity.random.nextInt(20, 30)),
                        new ThrowBlockAttack<SnowTrollEntity>(43)
                                .attackInterval(snowTrollEntity -> 60)
                                .startCondition(snowTrollEntity -> snowTrollEntity.distanceToSqr(snowTrollEntity.getTarget()) > 16)
                                .runFor(snowTrollEntity -> 60)
                                .whenStarting(snowTrollEntity -> {
                                    snowTrollEntity.setAttackState((byte) 2);
                                    snowTrollEntity.triggerAnim("controller", "throw");
                                })
                                .whenStopping(snowTrollEntity -> snowTrollEntity.setAttackState((byte) 0))
                                .cooldownFor(snowTrollEntity -> snowTrollEntity.random.nextInt(30, 40))
                )
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 1, this::controller)
                .triggerableAnim("sniff", SNIFF)
                .triggerableAnim("slam", SLAM)
                .triggerableAnim("throw", THROW)
                .triggerableAnim("death", DEATH));
    }

    protected <E extends SnowTrollEntity> PlayState controller(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(WALK);
        }
        return event.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
