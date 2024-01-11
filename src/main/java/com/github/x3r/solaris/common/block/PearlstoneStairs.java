package com.github.x3r.solaris.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

@SuppressWarnings("deprecated")
public class PearlstoneStairs extends StairBlock {

    public PearlstoneStairs(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
