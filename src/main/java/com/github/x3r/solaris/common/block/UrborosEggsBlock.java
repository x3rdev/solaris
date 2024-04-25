package com.github.x3r.solaris.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class UrborosEggsBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static VoxelShape north_shape;
    protected static VoxelShape east_shape;
    protected static VoxelShape south_shape;
    protected static VoxelShape west_shape;


    public UrborosEggsBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pNewState.isAir()) {
            AreaEffectCloud cloud = new AreaEffectCloud(pLevel, pPos.getX()+0.5F, pPos.getY()+0.5F, pPos.getZ()+0.5F);
            cloud.setPotion(Potions.POISON);
            cloud.setDuration(5);
            cloud.setRadius(1F);
            cloud.setFixedColor(0x4f662b);
            pLevel.addFreshEntity(cloud);
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)) {
            case NORTH:
                if(north_shape == null) {
                    north_shape = makeShape();
                }
                return north_shape;
            case SOUTH:
                if(south_shape == null) {
                    south_shape = BlockUtil.rotateShape(Direction.SOUTH, Direction.NORTH, makeShape());
                }
                return south_shape;
            case EAST:
                if(east_shape == null) {
                    east_shape = BlockUtil.rotateShape(Direction.EAST, Direction.NORTH, makeShape());
                }
                return east_shape;
            case WEST:
                if(west_shape == null) {
                    west_shape = BlockUtil.rotateShape(Direction.WEST, Direction.NORTH, makeShape());
                }
                return west_shape;
            default:
                return Shapes.empty();
        }
    }

    protected static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.5, 0.00125, 0.4375, 0.625, 0.25125, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.00125, 0.375, 0.6875, 0.37625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0.00125, 0.6875, 0.375, 0.12625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.00125, 0.625, 0.4375, 0.25125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.000625, 0, 1, 0.000625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.00125, 0.1875, 0.4375, 0.06375, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.00125, 0.125, 0.5, 0.18875, 0.3125), BooleanOp.OR);
        return shape;
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
