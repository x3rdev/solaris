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

    private final PerlinSimplexNoise islandNoise;
    private final PerlinSimplexNoise biomeNoise;
    private final PerlinSimplexNoise subBiomeNoise;
    private final PerlinSimplexNoise hillNoise;
    private final PerlinSimplexNoise undersideNoise;

    public static final ResourceKey<DensityFunction> SOLARIS_DENSITY = ResourceKey.create(Registries.DENSITY_FUNCTION,
            new ResourceLocation(Solaris.MOD_ID, "solaris_density"));
    public static final KeyDispatchDataCodec<SolarisDensityFunction> CODEC =
            KeyDispatchDataCodec.of(MapCodec.unit(new SolarisDensityFunction(0L)));

    public SolarisDensityFunction(long pSeed) {
        XoroshiroRandomSource source = new XoroshiroRandomSource(pSeed);
        source.consumeCount(15234);
        this.islandNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 2));
        this.biomeNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-10, 1));
        this.subBiomeNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-8, 1));
        this.hillNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-4, 1, 1));
        this.undersideNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-3, 1, 2));
    }

    @Override
    public double compute(FunctionContext context) {
        int heightMin = (int) (50 - 10 - (8*undersideNoise.getValue(context.blockX(), context.blockZ(), false)));
        int heightMax = (int) (50 + 5 + (4*hillNoise.getValue(context.blockX(), context.blockZ(), false)));
        if(context.blockY() < heightMin || context.blockY() > heightMax || context.blockY() <= 10) {
            return 0;
        }
        return 1;
    }

    public double biomeCompute(int x, int z) {
        double a = -noiseValue(islandNoise, x, z);
        if(a > 0.15) {
            return Mth.clamp(Math.abs(noiseValue(biomeNoise, x, z)*2), 0, 1);
        }
        return 0;
    }

    public double subBiomeCompute(int x, int z) {
        return Mth.clamp(Math.abs(noiseValue(subBiomeNoise, x, z)), 0, 1);
    }

    public double noiseValue(PerlinSimplexNoise noise, int x, int z) {
        int size = 2;
        double v = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                v += noise.getValue((double) x+i, (double) z+j, true);
            }
        }
        return v/(size*size);
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


