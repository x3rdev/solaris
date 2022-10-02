package com.github.x3rmination.solaris.common.block;

import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

@SuppressWarnings("deprecated")
public class PearlstoneBlock extends Block {


    public PearlstoneBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if(pLevel.getBrightness(LightLayer.BLOCK, pPos.above()) > 5) {
            pLevel.sendParticles(ParticleRegistry.ANIMATED_SPARKS.get(), pPos.getX() + 0.5D, pPos.getY(), pPos.getZ() + 0.5D, 10, 0, 0, 0, 0.4D);
        }
    }

}
