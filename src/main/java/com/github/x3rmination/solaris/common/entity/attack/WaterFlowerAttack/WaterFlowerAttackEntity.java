package com.github.x3rmination.solaris.common.entity.attack.WaterFlowerAttack;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class WaterFlowerAttackEntity extends Projectile implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public WaterFlowerAttackEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noCulling = true;
    }

    public WaterFlowerAttackEntity(LivingEntity pShooter) {
        this(EntityRegistry.WATER_FLOWER_ATTACK.get(), pShooter.level);
        this.setPos(pShooter.position().add(pShooter.getLookAngle()));
        this.setOwner(pShooter);
        this.setRot(pShooter.getYRot(), pShooter.getXRot());
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

        if(this.tickCount > 400) {
            this.kill();
        }
        this.level.getEntities(this, this.getBoundingBox().inflate(1.5)).forEach(entity -> {
            entity.hurt(DamageSource.GENERIC, 4);
        });
        createParticles();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(this.level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SPLASH, this.getX(), this.getY(), this.getZ(), 100, 0, 0.5, 0, 0.5);
            serverLevel.sendParticles(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 5, 0, 0.5, 0, 0.5);
        }
        this.kill();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.water_flower_skill.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private void createParticles() {
        ParticleHelper helper = new ParticleHelper(this.level, ParticleTypes.SPLASH, this.position());
        if(this.level instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 8; i++) {
                double v = (i * Math.PI/4) + this.tickCount/15F;
                double particleX = Math.cos(v) * 3;
                double particleZ = Math.sin(v) * 3;
                Vec3 speedVector = new Vec3(particleX, 0, particleZ).scale(0.25);
                helper.spawnParticle(serverLevel, new Vec3(this.getX() + particleX, this.getY(), this.getZ() + particleZ), speedVector.add(this.getDeltaMovement()));
            }
        }
    }
}
