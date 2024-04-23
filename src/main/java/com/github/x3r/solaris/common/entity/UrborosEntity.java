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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class UrborosEntity extends Monster implements SmartBrainOwner<UrborosEntity>, GeoEntity, RangedAttackMob {

    public static final EntityDataAccessor<Byte> ATTACK_STATE = SynchedEntityData.defineId(SnowTrollEntity.class, EntityDataSerializers.BYTE);

    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public UrborosEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.ARMOR, 2F)
                .add(Attributes.ATTACK_DAMAGE, 30.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 1.1F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F)
                .add(Attributes.MAX_HEALTH, 25.0F)
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
    public List<? extends ExtendedSensor<? extends UrborosEntity>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends UrborosEntity> getCoreTasks() {
        return SmartBrainOwner.super.getCoreTasks();
    }

    @Override
    public BrainActivityGroup<? extends UrborosEntity> getIdleTasks() {
        return SmartBrainOwner.super.getIdleTasks();
    }

    @Override
    public BrainActivityGroup<? extends UrborosEntity> getFightTasks() {
        return SmartBrainOwner.super.getFightTasks();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


}
