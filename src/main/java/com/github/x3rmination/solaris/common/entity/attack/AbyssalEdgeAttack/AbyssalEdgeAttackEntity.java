package com.github.x3rmination.solaris.common.entity.attack.AbyssalEdgeAttack;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.helper.physics.RBB;
import com.github.x3rmination.solaris.common.registry.EntityRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.common.extensions.IForgeEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Arrays;

public class AbyssalEdgeAttackEntity extends Entity implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public AbyssalEdgeAttackEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noCulling = true;
    }

    public AbyssalEdgeAttackEntity(Level pLevel) {
        super(EntityRegistry.ABYSSAL_EDGE_ATTACK.get(), pLevel);
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.tickCount > 100) {
            this.kill();
            return;
        }
        RBB attackBox = new RBB(new AABB(this.getX() - 1, this.getY() - 10, this.getZ() - 45, this.getX() + 1, this.getY() + 10, this.getZ() + 45), -Math.toRadians(this.getYRot()));
        attackBox.getEntitiesInRBB(this, level).forEach(entity -> {
            entity.setSecondsOnFire(2);
        });


    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.getController().isJustStarting || event.animationTick < 20) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.abyssal_edge_attack.opening", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            return PlayState.CONTINUE;
        }
        if(event.animationTick > 80) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.abyssal_edge_attack.closing", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.abyssal_edge_attack.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }
}
