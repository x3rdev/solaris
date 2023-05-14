package com.github.x3rmination.solaris.common.entity.attack.FrostbiteAttack;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import com.github.x3rmination.solaris.common.scheduler.ClientScheduler;
import com.github.x3rmination.solaris.common.scheduler.Executable;
import com.github.x3rmination.solaris.common.scheduler.ServerScheduler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

public class FrostbiteAttackEntity extends AbstractHurtingProjectile {

    public FrostbiteAttackEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FrostbiteAttackEntity(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(EntityRegistry.FROSTBITE_ATTACK.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    @Override
    public void tick() {
        this.xPower = 0;
        this.yPower = 0;
        this.zPower = 0;
        super.tick();
        if(this.tickCount > 200) {
            this.kill();
        }
        this.level.getEntities(this, this.getBoundingBox().inflate(2)).forEach(entity -> {
            if(entity instanceof LivingEntity livingEntity) {
                FrostbiteMobEffect.applyEffect(livingEntity);
                int age = this.tickCount;
                livingEntity.push(0, 0.5, 0);
            }
        });
        Vec3 dm = this.getDeltaMovement();
        for(int i = 0; i < 2; i++) {
            this.level.addParticle(ParticleRegistry.SNOW_TORNADO.get(), this.getX() + 0.5 * (this.random.nextFloat() - 0.5), this.getY() + 0.5 * (this.random.nextFloat() - 0.5), this.getZ() + 0.5 * (this.random.nextFloat() - 0.5), dm.x, dm.y, dm.z);
        }
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
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SNOWFLAKE;
    }
}
