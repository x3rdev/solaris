package com.github.x3rmination.solaris.common.entity.attack.CloudSplitterAttack;

import com.github.x3rmination.solaris.client.particle.option.AirTornadoOption;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CloudSplitterAttackEntity extends AbstractHurtingProjectile {

    public CloudSplitterAttackEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CloudSplitterAttackEntity(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(EntityRegistry.CLOUD_SPLITTER_ATTACK.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
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
            if(entity instanceof LivingEntity livingEntity && !entity.equals(this.getOwner())) {
                livingEntity.push(0, 0.5, 0);
            }
        });
        Vec3 dm = this.getDeltaMovement();
        for(int i = 0; i < 60; i+=5) {
            this.level.addParticle(new AirTornadoOption(i), this.getX() + 0.5 * (this.random.nextFloat() - 0.5), this.getY() + 0.5 * (this.random.nextFloat() - 0.5), this.getZ() + 0.5 * (this.random.nextFloat() - 0.5), dm.x, dm.y, dm.z);
        }
        for(int i = 0; i < 4; i++) {
            if(this.random.nextFloat() < 0.1) {
                this.level.addParticle(ParticleTypes.SWEEP_ATTACK, this.getX() + this.random.nextFloat() - 0.5, this.getY() + this.random.nextFloat() - 0.5 + i + 1, this.getZ() + this.random.nextFloat(), dm.x, dm.y, dm.z);
            }
        }
        if(this.isInWall()) {
            this.move(MoverType.SELF, new Vec3(0, 0.25, 0));
        } else {
            this.move(MoverType.SELF, new Vec3(0, -0.25, 0));
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
