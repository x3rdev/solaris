package com.github.x3rmination.solaris.common.entity.attack.BlizzardAttack;

import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlizzardAttackEntity extends AbstractHurtingProjectile {

    public BlizzardAttackEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BlizzardAttackEntity(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(EntityRegistry.BLIZZARD_ATTACK.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    @Override
    public void tick() {
        this.setDeltaMovement(0,0,0);
        super.tick();
        if(this.tickCount > 100) {
            this.kill();
        }
        for(int i = 0; i < 10; i++) {
            Vec3 pos = new Vec3(
                    this.position().x + (this.random.nextFloat() - 0.5) * (this.getBoundingBox().getXsize()),
                    this.position().y + 4 + this.random.nextFloat(),
                    this.position().z + (this.random.nextFloat() - 0.5) * (this.getBoundingBox().getZsize())
            );
            Vec3 vel = new Vec3(0.5*(this.random.nextFloat() - 0.5), -0.5, this.random.nextFloat() - 0.5);
            this.level.addParticle(ParticleTypes.SNOWFLAKE, pos.x, pos.y, pos.z, vel.x, vel.y, vel.z);
        }
        this.level.getEntities(this, this.getBoundingBox()).forEach(entity -> {
            if(entity instanceof LivingEntity livingEntity && !entity.equals(this.getOwner())) {
                FrostbiteMobEffect.applyEffect(livingEntity);
                livingEntity.setDeltaMovement(0.5*-(this.getX()-livingEntity.getX()), 0.25, 0.5*-(this.getZ()-livingEntity.getZ()));
            }
        });
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
