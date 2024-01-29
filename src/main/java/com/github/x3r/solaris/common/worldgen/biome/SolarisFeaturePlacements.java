package com.github.x3r.solaris.common.worldgen.biome;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class SolarisFeaturePlacements {

    public static List<PlacementModifier> treePlacement(PlacementModifier placement) {
        return ImmutableList.<PlacementModifier>builder().add(placement).add(InSquarePlacement.spread()).add(PlacementUtils.HEIGHTMAP_WORLD_SURFACE).add(BiomeFilter.biome()).build();
    }

    public static List<PlacementModifier> grassPlacement(PlacementModifier placement) {
        return ImmutableList.<PlacementModifier>builder().add(placement).add(InSquarePlacement.spread()).add(PlacementUtils.HEIGHTMAP_WORLD_SURFACE).add(BiomeFilter.biome()).build();
    }
}
