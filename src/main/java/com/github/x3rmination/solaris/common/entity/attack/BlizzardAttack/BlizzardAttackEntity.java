package com.github.x3rmination.solaris.common.entity.attack.BlizzardAttack;

import com.github.x3rmination.solaris.client.particle.option.BlizzardOption;
import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

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
        if(this.tickCount > 200) {
            this.kill();
        }
        this.level.getEntities(this, this.getBoundingBox().inflate(4)).forEach(entity -> {
            if(entity instanceof LivingEntity livingEntity && !entity.equals(this.getOwner())) {
                FrostbiteMobEffect.applyEffect(livingEntity);
            }
        });
        if(this.tickCount <= 100) {
            for (int i = 0; i < 10; i++) {
                this.level.addParticle(new BlizzardOption(1), this.getX(), this.getY() + 0.1 + i / 2F, this.getZ(), 0, 0, 0);
            }
        }
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
