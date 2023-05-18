package com.github.x3rmination.solaris.common.entity.attack.FireKatanaAttack;

import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class FireKatanaAttackEntity extends Projectile implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public FireKatanaAttackEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noCulling = true;
    }

    public FireKatanaAttackEntity(LivingEntity pShooter) {
        this(EntityRegistry.FIRE_KATANA_ATTACK.get(), pShooter.level);
        this.setOwner(pShooter);
        this.setPos(pShooter.position().add(pShooter.getLookAngle().scale(1.25).add(0, 0.75, 0)));
        this.setRot(pShooter.getYHeadRot(), pShooter.getXRot());
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();
        this.setPos(this.position().add(this.getDeltaMovement()));
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        if(this.tickCount > 600) {
            this.kill();
        }
        this.level.getEntities(this, this.getBoundingBox().inflate(1.5)).forEach(entity -> {
            entity.setRemainingFireTicks(10);
            entity.hurt(DamageSource.GENERIC, 4);
        });
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(this.level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleRegistry.FLAME_0.get(), this.getX(), this.getY(), this.getZ(), 100, 0, 0.5, 0, 0.25);
        }
        this.kill();
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
