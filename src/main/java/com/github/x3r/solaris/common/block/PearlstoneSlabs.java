package com.github.x3r.solaris.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("deprecated")
public class PearlstoneSlabs extends SlabBlock {

    public PearlstoneSlabs(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
