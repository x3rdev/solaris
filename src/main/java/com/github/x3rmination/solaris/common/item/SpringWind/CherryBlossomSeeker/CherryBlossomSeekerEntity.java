package com.github.x3rmination.solaris.common.item.SpringWind.CherryBlossomSeeker;

import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CherryBlossomSeekerEntity extends Projectile {

    public CherryBlossomSeekerEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }

    public CherryBlossomSeekerEntity(Level pLevel, LivingEntity pShooter) {
        this(EntityRegistry.CHERRY_BLOSSOM_SEEKER.get(), pLevel);
        this.setOwner(pShooter);
    }

                         @Override
    public void tick() {
        super.tick();
        this.level.addParticle(ParticleRegistry.CHERRY_BLOSSOM.get(), this.getX(), this.getY(), this.getZ(), (this.random.nextFloat() - 0.5F) * 0.05, 0.05, (this.random.nextFloat() - 0.5F) * 0.05);
        if(!this.level.isClientSide) {
            if (this.tickCount > 200 || this.getOwner() == null) {
                this.kill();
                //Maybe do some animation or some shit
            } else {
                List<Entity> seekTargets = getTargets();
                if(seekTargets.isEmpty()) {
                    //Idle animation
                } else {
                    incrementLookAt(EntityAnchorArgument.Anchor.EYES, seekTargets.get(0).position().add(0, 1, 0));
                    Vec3 posIncrement = this.getLookAngle().scale(0.1);
                    this.moveTo(this.position().add(posIncrement));
                }
            }
        }
    }

    public void incrementLookAt(EntityAnchorArgument.Anchor pAnchor, Vec3 pTarget) {
        Vec3 vec3 = pAnchor.apply(this);
        double d0 = pTarget.x - vec3.x;
        double d1 = pTarget.y - vec3.y;
        double d2 = pTarget.z - vec3.z;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float xDegrees = Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * (double)(180F / (float)Math.PI)))) - getXRot();
        float yDegrees = Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F) - getYRot();
        float modifier = 0.5F;
        this.setXRot(xDegrees * modifier + getXRot());
        this.setYRot(yDegrees * modifier + getYRot());
        this.setYHeadRot(this.getYRot());
        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
    }


    public List<Entity> getTargets() {
        Vec3 ownerPos = this.getOwner().position();
        AABB seekingArea = new AABB(ownerPos.x - 10, ownerPos.y - 10, ownerPos.z - 10, ownerPos.x + 10, ownerPos.y + 10, ownerPos.z + 10);
        List<Entity> seekTargets = level.getEntities(this.getOwner(), seekingArea);
        seekTargets.removeIf(CherryBlossomSeekerEntity.class::isInstance);
        return seekTargets;
    }

    @Override
    public void kill() {
        //Some funny particles
        for(int i = 0; i < 10; i++) {
            this.level.addParticle(ParticleRegistry.CHERRY_BLOSSOM.get(), this.getX(), this.getY(), this.getZ(), this.random.nextFloat() - 0.5F, this.random.nextFloat() - 0.5F, this.random.nextFloat() - 0.5F);
        }
        super.kill();
    }

    @Override
    public void defineSynchedData() {
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
