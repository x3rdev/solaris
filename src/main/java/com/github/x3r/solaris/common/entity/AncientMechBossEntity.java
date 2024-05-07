package com.github.x3r.solaris.common.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class AncientMechBossEntity extends Monster implements SmartBrainOwner<AncientMechBossEntity>, GeoEntity {

    public static final EntityDataAccessor<Byte> ATTACK_STATE = SynchedEntityData.defineId(SnowTrollEntity.class, EntityDataSerializers.BYTE);

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public AncientMechBossEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
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
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends AncientMechBossEntity>> getSensors() {
        return List.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends AncientMechBossEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<AncientMechBossEntity>()
                        .startCondition(ancientMech ->
                                ancientMech.getAttackState() == 0 &&
                                        (
                                                ancientMech.getTarget() != null &&
                                                        !ancientMech.getTarget().isDeadOrDying() &&
                                                        !ancientMech.isDeadOrDying()
                                        )
                        )
                        .stopIf(ancientMech ->
                                ancientMech.getAttackState() != 0 ||
                                        (
                                                ancientMech.getTarget() == null ||
                                                        ancientMech.getTarget().isDeadOrDying() ||
                                                        ancientMech.isDeadOrDying()
                                        )
                        )
        );
    }

    @Override
    public BrainActivityGroup<? extends AncientMechBossEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new TargetOrRetaliate<>()
                                .attackablePredicate(livingEntity ->
                                        livingEntity instanceof Player ||
                                                livingEntity instanceof Creeper),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()
                )
        );
    }

    @Override
    public BrainActivityGroup<? extends AncientMechBossEntity> getFightTasks() {
        return SmartBrainOwner.super.getFightTasks();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
