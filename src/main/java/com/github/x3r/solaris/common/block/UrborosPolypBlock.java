package com.github.x3r.solaris.common.block;

import com.github.x3r.solaris.common.block_entity.UrborosPolypBlockEntity;
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

public class UrborosPolypBlock extends Block implements EntityBlock {

    protected static VoxelShape shape;
    public UrborosPolypBlock(Properties pProperties) {
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
        return new UrborosPolypBlockEntity(pPos, pState);
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
        shape = Shapes.join(shape, Shapes.box(0.34375, 0.375, 0.34375, 0.65625, 0.5625, 0.65625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.65625, 0.25, 0.5, 0.84375, 0.5, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.5625, 0.40625, 0.375, 0.6875, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.8125, 0.46875, 0.375, 0.9375, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.6875, 0.46875, 0.375, 0.8125, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.15625, 0.25, 0.5, 0.34375, 0.5, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5598191738241592, 0.5625, 0.4946383476483186, 0.5598191738241592, 0.6875, 0.6821383476483186), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5598191738241592, 0.8125, 0.5571383476483186, 0.5598191738241592, 0.9375, 0.6196383476483186), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5598191738241592, 0.6875, 0.5571383476483186, 0.5598191738241592, 0.8125, 0.6196383476483186), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5636104345603983, 0.5625, 0.3178616523516815, 0.5636104345603983, 0.6875, 0.5053616523516815), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5636104345603983, 0.8125, 0.38036165235168146, 0.5636104345603983, 0.9375, 0.4428616523516814), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5636104345603983, 0.6875, 0.38036165235168146, 0.5636104345603983, 0.8125, 0.4428616523516814), BooleanOp.OR);

        return shape;
    }
}
