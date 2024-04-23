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
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Vector2i;
import yesman.epicfight.api.utils.math.Vec2i;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SolarisDensityFunction implements DensityFunction.SimpleFunction {

    private final IslandNoise islandNoise1;
    private final IslandNoise islandNoise2;
    private final IslandNoise islandNoise3;
    private final IslandNoise islandNoise4;

    public static final ResourceKey<DensityFunction> SOLARIS_DENSITY = ResourceKey.create(Registries.DENSITY_FUNCTION,
            new ResourceLocation(Solaris.MOD_ID, "solaris_density"));
    public static final KeyDispatchDataCodec<SolarisDensityFunction> CODEC =
            KeyDispatchDataCodec.of(MapCodec.unit(new SolarisDensityFunction(0L)));

    public SolarisDensityFunction(long pSeed) {
        this.islandNoise1 = new IslandNoise(new LegacyRandomSource(pSeed+1), 128, 0F, 0.5F, 96);
        this.islandNoise2 = new IslandNoise(new LegacyRandomSource(pSeed+2), 128, 0.25F, 0.2F, 125);
        this.islandNoise3 = new IslandNoise(new LegacyRandomSource(pSeed+3), 128, 0.75F, 0.2F, 125);
        this.islandNoise4 = new IslandNoise(new LegacyRandomSource(pSeed+4), 128, 0.5F, 0.25F, 110);
    }

    @Override
    public double compute(FunctionContext context) {
        double d = Stream.of(
                islandNoise1.getValue(context.blockX(), context.blockY(), context.blockZ()),
                islandNoise2.getValue(context.blockX(), context.blockY(), context.blockZ()),
                islandNoise3.getValue(context.blockX(), context.blockY(), context.blockZ()),
                islandNoise4.getValue(context.blockX(), context.blockY(), context.blockZ())
        ).max(Double::compare).orElseThrow();
        return d > 0.6 ? 1 : -1;
    }

    public Vector2i biomeCompute(FunctionContext context) {
        IslandNoise noise = Stream.of(
                Pair.of(islandNoise1, islandNoise1.getValue(context.blockX(), context.blockZ())),
                Pair.of(islandNoise2, islandNoise2.getValue(context.blockX(), context.blockZ())),
                Pair.of(islandNoise3, islandNoise3.getValue(context.blockX(), context.blockZ())),
                Pair.of(islandNoise4, islandNoise4.getValue(context.blockX(), context.blockZ()))
        ).max(Comparator.comparing(Pair::getRight)).orElseThrow().getLeft();
        return noise.getClosestCenter(context.blockX(), context.blockZ());
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


