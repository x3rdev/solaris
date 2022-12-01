package com.github.x3rmination.solaris.common.item.SpringWind.CherryBlossomSeeker;

import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CherryBlossomSeekerEntity extends Projectile {

    private static final EntityDataAccessor<Float> DATA_OFFSET = SynchedEntityData.defineId(CherryBlossomSeekerEntity.class, EntityDataSerializers.FLOAT);
    private Entity attackTarget;

    public CherryBlossomSeekerEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }

    public CherryBlossomSeekerEntity(Level pLevel, LivingEntity pShooter, float v) {
        this(EntityRegistry.CHERRY_BLOSSOM_SEEKER.get(), pLevel);
        this.setOwner(pShooter);
        this.entityData.set(DATA_OFFSET, v);
    }

    @Override
    public void tick() {
        super.tick();
        this.level.addParticle(ParticleRegistry.CHERRY_BLOSSOM.get(), this.getX(), this.getY(), this.getZ(), (this.random.nextFloat() - 0.5F) * 0.03, 0.05, (this.random.nextFloat() - 0.5F) * 0.03);
        this.level.addParticle(ParticleRegistry.CHERRY_BLOSSOM.get(), this.getX(), this.getY() - 0.25, this.getZ(), (this.random.nextFloat() - 0.5F) * 0.03, 0.03, (this.random.nextFloat() - 0.5F) * 0.03);
        this.level.addParticle(ParticleRegistry.CHERRY_BLOSSOM.get(), this.getX(), this.getY() + 0.25, this.getZ(), (this.random.nextFloat() - 0.5F) * 0.03, 0.03, (this.random.nextFloat() - 0.5F) * 0.03);

        tryHurt();
        if(!this.level.isClientSide) {
            if (this.tickCount > 20 * 20 || this.getOwner() == null) {
                this.kill();
            } else {
                if(this.attackTarget == null || !this.attackTarget.isAlive()) {
                    Vec3 ownerPos = getOwner().position();
                    moveToSmooth(new Vec3(ownerPos.x + 4 * Math.cos(tickCount/20F + this.entityData.get(DATA_OFFSET)), ownerPos.y + this.random.nextFloat(2, 2.5F), ownerPos.z + 4 * Math.sin(tickCount/20F + this.entityData.get(DATA_OFFSET))), 0.8);
                } else {
                    moveToSmooth(this.attackTarget.position().add(0, 1, 0), 0.5);
                }
            }
        }
    }

    public void setTarget(Entity target) {
        if(target != null) {
            this.attackTarget = target;
        }
    }

    public void tryHurt() {
        AABB hurtBox = new AABB(this.getX() - 0.5, this.getY() - 0.5, this.getZ() - 0.5, this.getX() + 0.5, this.getY() + 0.5, this.getZ() + 0.5);
        List<Entity> collisionList = level.getEntities(this, hurtBox);
        collisionList.forEach(entity -> {
            if (entity == this.attackTarget) {
                entity.hurt(DamageSource.indirectMagic(entity, this.getOwner()), this.random.nextInt(3, 6));
            }
        });
    }

    public void moveToSmooth(Vec3 pos, double speed) {
        incrementLookAt(EntityAnchorArgument.Anchor.EYES, pos);
        Vec3 posIncrement = this.getLookAngle().scale(speed);
        if(distanceToSqr(pos) < Math.abs(posIncrement.lengthSqr())) {
            this.moveTo(pos);
        } else {
            this.moveTo(this.position().add(posIncrement));
        }
    }

    public void incrementLookAt(EntityAnchorArgument.Anchor pAnchor, Vec3 pTarget) {
        Vec3 vec3 = pAnchor.apply(this);
        double d0 = pTarget.x - vec3.x;
        double d1 = pTarget.y - vec3.y;
        double d2 = pTarget.z - vec3.z;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float xDegrees = (float)(-(Mth.atan2(d1, d3) * (180F / (float)Math.PI))) - getXRot();
        float yDegrees = (float)(Mth.atan2(d2, d0) * (180F / (float)Math.PI)) - 90.0F - getYRot();
        float modifier = 0.9F;
        this.setXRot(Mth.wrapDegrees(xDegrees * modifier + getXRot()));
        this.setYRot(Mth.wrapDegrees(yDegrees * modifier + getYRot()));
        this.setYHeadRot(this.getYRot());
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }

    @Override
    public void kill() {
        for(int i = 0; i < 10; i++) {
            this.level.addParticle(ParticleRegistry.CHERRY_BLOSSOM.get(), this.getX(), this.getY(), this.getZ(), (this.random.nextFloat() - 0.5F) * 2, (this.random.nextFloat() - 0.5F) * 2, (this.random.nextFloat() - 0.5F) * 2);
        }
        super.kill();
    }

    @Override
    public void defineSynchedData() {
        this.entityData.define(DATA_OFFSET, 0F);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }


}
