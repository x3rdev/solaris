package com.github.x3r.solaris.common.worldgen.dimension;

import com.github.x3r.solaris.Solaris;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

import java.util.List;

public class SolarisDensityFunction implements DensityFunction.SimpleFunction {

    private final PerlinSimplexNoise waterIslandsNoise;
    private final PerlinSimplexNoise fireIslandsNoise;
    private final PerlinSimplexNoise natureIslandsNoise;
    private final PerlinSimplexNoise lightningIslandsNoise;
    private final PerlinSimplexNoise lightIslandsNoise;

    public static final ResourceKey<DensityFunction> SOLARIS_DENSITY = ResourceKey.create(Registries.DENSITY_FUNCTION,
            new ResourceLocation(Solaris.MOD_ID, "solaris_density"));
    public static final KeyDispatchDataCodec<SolarisDensityFunction> CODEC =
            KeyDispatchDataCodec.of(MapCodec.unit(new SolarisDensityFunction(0L)));

    public SolarisDensityFunction(long pSeed) {
        XoroshiroRandomSource source = new XoroshiroRandomSource(pSeed);
        source.consumeCount(15234);
        this.waterIslandsNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 0, 1, 0, -2));
        this.fireIslandsNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 0, 1, 0, -2));
        this.natureIslandsNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 0, 1, 0, -2));
        this.lightningIslandsNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 0, 1, 0, -2));
        this.lightIslandsNoise = new PerlinSimplexNoise(new LegacyRandomSource(source.nextInt()), List.of(-7, 1, 0, 1, 0, -2));
    }

    @Override
    public double compute(FunctionContext context) {
        int x = context.blockX();
        int z = context.blockZ();
        double n1 = noiseValue(waterIslandsNoise, x, z);
        double n2 = noiseValue(fireIslandsNoise, x, z);
        double n3 = noiseValue(natureIslandsNoise, x, z);
        double n4 = noiseValue(lightningIslandsNoise, x, z);
        double n5 = noiseValue(lightIslandsNoise, x, z);
        List<Double> doubles = List.of(n1, n2, n3, n4, n5);
        double val = -1;
        int it = 0;
        for (int i = 0; i < doubles.size(); i++) {
            if(doubles.get(i) > val) {
                val = doubles.get(i);
                if(++it > 1) {
                    return -1;
                }
            }
        }
        if(context.blockY() < 30 || context.blockY() > 50) {
            return -1;
        }
        return val;
    }

    public byte biomeCompute(int x, int z) {
        double n1 = noiseValue(waterIslandsNoise, x, z);
        double n2 = noiseValue(fireIslandsNoise, x, z);
        double n3 = noiseValue(natureIslandsNoise, x, z);
        double n4 = noiseValue(lightningIslandsNoise, x, z);
        double n5 = noiseValue(lightIslandsNoise, x, z);
        List<Double> doubles = List.of(n1, n2, n3, n4, n5);
        double val = -1;
        byte biomeIndex = -1;
        int it = 0;
        for (int i = 0; i < doubles.size(); i++) {
            if(doubles.get(i) > val) {
                val = doubles.get(i);
                biomeIndex = (byte) i;
                if(++it > 1) {
                    return -1;
                }
            }
        }
        return biomeIndex;
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


