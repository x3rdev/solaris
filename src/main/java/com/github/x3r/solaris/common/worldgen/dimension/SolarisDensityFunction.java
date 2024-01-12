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
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

import java.util.List;

public class SolarisDensityFunction implements DensityFunction.SimpleFunction {

    private final PerlinSimplexNoise islandNoise;
    private final PerlinSimplexNoise biomeNoise;

    public static final ResourceKey<DensityFunction> SOLARIS_DENSITY = ResourceKey.create(Registries.DENSITY_FUNCTION,
            new ResourceLocation(Solaris.MOD_ID, "solaris_density"));
    public static final KeyDispatchDataCodec<SolarisDensityFunction> CODEC =
            KeyDispatchDataCodec.of(MapCodec.unit(new SolarisDensityFunction(0L)));

    public SolarisDensityFunction(long pSeed) {
        XoroshiroRandomSource source = new XoroshiroRandomSource(pSeed);
        source.consumeCount(15234);
        this.islandNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 0, 0, 1));
        this.biomeNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-10, -1));
    }

    @Override
    public double compute(FunctionContext context) {
        return 1;
    }

    public byte biomeCompute(int x, int z) {
        double a = -noiseValue(islandNoise, x, z);
        if(a > 0.05) {
            double b = Mth.clamp(Math.abs(noiseValue(biomeNoise, x, z)), 0, 1);
            return (byte) (1+(b*4));
        }
        return 0;
    }

    public double noiseValue(PerlinSimplexNoise noise, int x, int z) {
        double v = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                v += noise.getValue(x, z, false);
            }
        }
        return v/4;
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


