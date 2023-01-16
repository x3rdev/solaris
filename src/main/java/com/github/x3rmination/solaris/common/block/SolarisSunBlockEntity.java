package com.github.x3rmination.solaris.common.block;

import com.github.x3rmination.solaris.common.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Random;

public class SolarisSunBlockEntity extends BlockEntity implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public int time;
    public SolarisSunBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.SOLARIS_SUN_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.time = new Random().nextInt(100000);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 1, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.solaris_sun.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public static void clientTick(Level pLevel, BlockPos pPos, BlockState pState, SolarisSunBlockEntity pBlockEntity) {
        pBlockEntity.time++;
    }
}
