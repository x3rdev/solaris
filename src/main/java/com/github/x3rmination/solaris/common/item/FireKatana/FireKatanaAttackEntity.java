package com.github.x3rmination.solaris.common.item.FireKatana;

import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class FireKatanaAttackEntity extends AbstractHurtingProjectile implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public FireKatanaAttackEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noCulling = true;
    }

    public FireKatanaAttackEntity(LivingEntity pShooter, Level pLevel) {
        super(EntityRegistry.FIRE_KATANA_ATTACK.get(), pShooter, 0, 0, 0, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount > 600) {
            this.kill();
        }
        this.level.getEntities(this, this.getBoundingBox()).forEach(entity -> {
            entity.setRemainingFireTicks(10);
            entity.hurt(DamageSource.GENERIC, 4);
        });
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.FLAME;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public float getInertia() {
        return 1;
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
