package com.github.x3r.solaris.common.block.solaris_sun;

import com.github.x3r.solaris.common.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class SolarisSunBlockEntity extends BlockEntity implements GeoAnimatable {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public int time;
    public SolarisSunBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.SOLARIS_SUN_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.time = new Random().nextInt(100000);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, SolarisSunBlockEntity pBlockEntity) {
        pBlockEntity.time++;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, animationState -> {
            animationState.getController().setAnimation(RawAnimation.begin().thenPlay("animation.solaris_sun.idle"));
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
