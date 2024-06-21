package com.github.x3r.solaris.common.block;

import com.github.x3r.solaris.common.registry.DimensionRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TrollCaveGateBlock extends DungeonGateBlock {
    public TrollCaveGateBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    boolean isSuitableKey(ItemStack stack) {
        return false;
    }

    @Override
    void useGate(Level pLevel, BlockPos pPos, Player pPlayer) {
        ServerLevel level = pLevel.getServer().getLevel(DimensionRegistry.TROLL_CAVE_LEVEL_KEY);
        if(level.players().isEmpty()) {

        }
    }
}
