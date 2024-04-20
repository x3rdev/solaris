package com.github.x3r.solaris.common.worldgen.dimension;

import com.github.x3r.solaris.Solaris;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

import java.util.List;

public class SolarisDensityFunction implements DensityFunction.SimpleFunction {

    private final IslandNoise islandNoise1;
    private final IslandNoise islandNoise2;

    public static final ResourceKey<DensityFunction> SOLARIS_DENSITY = ResourceKey.create(Registries.DENSITY_FUNCTION,
            new ResourceLocation(Solaris.MOD_ID, "solaris_density"));
    public static final KeyDispatchDataCodec<SolarisDensityFunction> CODEC =
            KeyDispatchDataCodec.of(MapCodec.unit(new SolarisDensityFunction(0L)));

    public SolarisDensityFunction(long pSeed) {
        this.islandNoise1 = new IslandNoise(new LegacyRandomSource(pSeed), 128, 0F, 0.5F);
        this.islandNoise2 = new IslandNoise(new LegacyRandomSource(pSeed), 128, 0.5F, 0.25F);
    }

    @Override
    public double compute(FunctionContext context) {
        double d = Math.max(islandNoise1.getValue(context.blockX(), context.blockZ()), islandNoise2.getValue(context.blockX(), context.blockZ()));
        return d > 0.6 ? 1 : -1;
    }

    @Override
    public double minValue() {
        return -1;
    }

    @Override
    public double maxValue() {
        return 1;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}


