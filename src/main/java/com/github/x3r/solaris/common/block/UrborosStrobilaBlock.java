package com.github.x3r.solaris.common.block;

import com.github.x3r.solaris.common.block_entity.UrborosPolypBlockEntity;
import com.github.x3r.solaris.common.block_entity.UrborosStrobilaBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class UrborosStrobilaBlock extends Block implements EntityBlock {

    protected static VoxelShape shape;
    public UrborosStrobilaBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (!pLevel.isClientSide()) {
            pLevel.scheduleTick(pPos, this, 1);
        }
        return pState;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!this.canSurvive(pState, pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos pos = pPos.below();
        BlockState state = pLevel.getBlockState(pos);
        return !state.getCollisionShape(pLevel, pos).getFaceShape(Direction.UP).isEmpty() || state.isFaceSturdy(pLevel, pos, Direction.UP);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new UrborosStrobilaBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(shape == null){
            shape = makeShape();
        }
        return shape;
    }

    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, 0.4375, 0.5625, 0.375, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.25, 0.34375, 0.65625, 0.5625, 0.65625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.75, 0.34375, 0.34375, 0.875, 0.65625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.875, 0.40625, 0.34375, 1, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 1, 0.46875, 0.34375, 1.125, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.28125, 0.375, 0.28125, 0.71875, 0.75, 0.71875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.09375, 0.1875, 0.5, 0.34375, 0.5625, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.65625, 0.1875, 0.5, 0.90625, 0.5625, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.34375, 1.125, 0.46875, 0.34375, 1.25, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.59375, 0.75, 0.234375, 0.59375, 0.875, 0.546875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.59375, 0.875, 0.296875, 0.59375, 1, 0.484375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.59375, 1, 0.359375, 0.59375, 1.125, 0.421875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.59375, 1.125, 0.359375, 0.59375, 1.25, 0.421875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.7122779140192428, 0.75, 0.2462087425233902, 0.7122779140192428, 0.875, 0.5587087425233902), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.7122779140192428, 0.875, 0.3087087425233903, 0.7122779140192428, 1, 0.4962087425233902), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.7122779140192428, 1, 0.3712087425233903, 0.7122779140192428, 1.125, 0.43370874252339026), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.7122779140192428, 1.125, 0.3712087425233903, 0.7122779140192428, 1.25, 0.43370874252339026), BooleanOp.OR);

        return shape;
    }
}
