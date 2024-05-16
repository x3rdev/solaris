package com.github.x3r.solaris.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AncientGlowingBricksBlock extends DirectionalBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");

    public AncientGlowingBricksBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(RIGHT, false)
                .setValue(DOWN, false)
                .setValue(LEFT, false)
                .setValue(FACING, Direction.SOUTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
        builder.add(UP);
        builder.add(RIGHT);
        builder.add(DOWN);
        builder.add(LEFT);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()))
                .setValue(UP, false)
                .setValue(RIGHT, false)
                .setValue(DOWN, false)
                .setValue(LEFT, false)
                .setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(POWERED);
            if (flag != hasNeighborSignal(pState, pLevel, pPos, null)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlockAndUpdate(pPos, pState.cycle(POWERED));
                }
            }
        }
    }

    public List<Direction> getConnectionDirections(BlockState state) {
        Direction direction = state.getValue(FACING);
        boolean up = state.getValue(UP);
        boolean right = state.getValue(RIGHT);
        boolean down = state.getValue(DOWN);
        boolean left = state.getValue(LEFT);
        List<Direction> l = new ArrayList<>();
        if(direction == Direction.NORTH) {
            if (up) {
                l.add(Direction.UP);
            }
            if (right) {
                l.add(Direction.WEST);
            }
            if (down) {
                l.add(Direction.DOWN);
            }
            if (left) {
                l.add(Direction.EAST);
            }
        }
        if (direction == Direction.EAST) {
            if (up) {
                l.add(Direction.UP);
            }
            if (right) {
                l.add(Direction.NORTH);
            }
            if (down) {
                l.add(Direction.DOWN);
            }
            if (left) {
                l.add(Direction.SOUTH);
            }
        }
        if (direction == Direction.SOUTH) {
            if (up) {
                l.add(Direction.UP);
            }
            if (right) {
                l.add(Direction.EAST);
            }
            if (down) {
                l.add(Direction.DOWN);
            }
            if (left) {
                l.add(Direction.WEST);
            }
        }
        if (direction == Direction.WEST) {
            if (up) {
                l.add(Direction.UP);
            }
            if (right) {
                l.add(Direction.SOUTH);
            }
            if (down) {
                l.add(Direction.DOWN);
            }
            if (left) {
                l.add(Direction.NORTH);
            }
        }
        if (direction == Direction.UP) {
            if (up) {
                l.add(Direction.SOUTH);
            }
            if (right) {
                l.add(Direction.WEST);
            }
            if (down) {
                l.add(Direction.NORTH);
            }
            if (left) {
                l.add(Direction.EAST);
            }
        }
        if (direction == Direction.DOWN) {
            if (up) {
                l.add(Direction.NORTH);
            }
            if (right) {
                l.add(Direction.WEST);
            }
            if (down) {
                l.add(Direction.SOUTH);
            }
            if (left) {
                l.add(Direction.EAST);
            }
        }
        l.add(direction);
        return l;
    }

    private boolean canConnect(BlockState state, Direction direction) {
        return getConnectionDirections(state).contains(direction);
    }

    private boolean hasNeighborSignal(BlockState state, Level level, BlockPos pos, @Nullable Direction blackListDir) {
        for (Direction direction : getConnectionDirections(state)) {
            if(blackListDir != null && direction == blackListDir) {
                continue;
            }
            if (hasSignal(level, pos, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSignal(Level level, BlockPos pos, Direction direction) {
        BlockPos neighborPos = pos.relative(direction);
        BlockState state = level.getBlockState(neighborPos);
        if(state.is(this)) {
            return canConnect(state, direction.getOpposite()) && hasNeighborSignal(state, level, neighborPos, direction.getOpposite());
        }
        return level.hasSignal(neighborPos, direction.getOpposite());
    }

    @Override
    public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection) {
        Direction direction = pState.getValue(FACING);
        return direction.equals(pDirection) && pState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlockAndUpdate(pPos, pState.setValue(POWERED, hasNeighborSignal(pState, pLevel, pPos, null)));
    }
}
