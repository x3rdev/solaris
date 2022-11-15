package com.github.x3rmination.solaris.common.item.Frostbite;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.github.x3rmination.solaris.common.scheduler.ClientScheduler;
import com.github.x3rmination.solaris.common.scheduler.Executable;
import com.github.x3rmination.solaris.common.scheduler.ServerScheduler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

public class FrostbiteAttackEntity extends AbstractHurtingProjectile implements SolarisWeapon {

    public FrostbiteAttackEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FrostbiteAttackEntity(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(EntityRegistry.FROSTBITE_ATTACK.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount > 20) {
            this.kill();
        }
        this.level.getEntities(this, this.getBoundingBox()).forEach(entity -> {
            if(entity instanceof LivingEntity livingEntity) {
                FrostbiteMobEffect.applyEffect(livingEntity);
            }
        });
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public float getInertia() {
        return 1;
    }

    @Override
    public void clientAttack(LocalPlayer localPlayer, Skill skill) throws NoSuchMethodException {
        if(skill.equals(Skills.FATAL_DRAW)) {
            ClientScheduler.schedule(new Executable(
                    this,
                    this.getClass().getDeclaredMethod("frostbiteClient", LocalPlayer.class),
                    new Object[]{localPlayer}, 35));
        }
    }

    public void frostbiteClient(LocalPlayer localPlayer) {
        ParticleHelper particleHelper = new ParticleHelper(localPlayer.level, ParticleTypes.SNOWFLAKE, localPlayer.position().add(0, 2, 0));
        float yMod = 0;
        while(yMod > -2) {
            particleHelper.spawnCircle(2 + yMod, 16, localPlayer.getLookAngle().multiply(1, 0, 1));
            particleHelper.setPos(particleHelper.getPos().add(0, -0.5, 0));
            yMod-=0.5;
        }
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) throws NoSuchMethodException {
        if(skill.equals(Skills.FATAL_DRAW)) {
            ServerScheduler.schedule(new Executable(
                    this,
                    this.getClass().getDeclaredMethod("frostbiteServer", ServerPlayer.class),
                    new Object[]{serverPlayer}, 35));
        }
    }

    public void frostbiteServer(ServerPlayer serverPlayer) {
        FrostbiteAttackEntity frostbiteAttackEntity = new FrostbiteAttackEntity(serverPlayer, serverPlayer.getLookAngle().x, 0, serverPlayer.getLookAngle().z, serverPlayer.level);
        frostbiteAttackEntity.setPos(serverPlayer.position().add(serverPlayer.getLookAngle().multiply(3, 0, 3)));
        serverPlayer.level.addFreshEntity(frostbiteAttackEntity);
    }
}
