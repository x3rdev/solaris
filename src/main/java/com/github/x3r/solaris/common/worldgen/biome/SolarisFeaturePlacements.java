package com.github.x3r.solaris.common.worldgen.biome;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class SolarisFeaturePlacements {

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier pPlacement) {
        return ImmutableList.<PlacementModifier>builder().add(pPlacement).add(InSquarePlacement.spread()).add(PlacementUtils.HEIGHTMAP_WORLD_SURFACE).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement) {
        return treePlacementBase(pPlacement).build();
    }
}
