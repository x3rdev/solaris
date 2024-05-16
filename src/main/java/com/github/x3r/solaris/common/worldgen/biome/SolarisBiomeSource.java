package com.github.x3r.solaris.common.worldgen.biome;

import com.github.x3r.solaris.common.registry.BiomeRegistry;
import com.github.x3r.solaris.common.worldgen.dimension.SolarisDensityFunction;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.joml.Vector2i;

import java.util.stream.Stream;

public class SolarisBiomeSource extends BiomeSource {

    private static final float SQRT2 = (float) Math.sqrt(2);

    public static final Codec<SolarisBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                RegistryOps.retrieveElement(BiomeRegistry.SCORCHED_PLAINS),
                RegistryOps.retrieveElement(BiomeRegistry.WATER_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.FIRE_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.NATURE_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.LIGHTNING_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.DARK_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.LIGHT_ISLANDS)
        ).apply(instance, instance.stable(SolarisBiomeSource::new));
    });
    private final Holder<Biome> scorchedPlains;
    private final Holder<Biome> waterIslands;
    private final Holder<Biome> fireIslands;
    private final Holder<Biome> natureIslands;
    private final Holder<Biome> lightningIslands;
    private final Holder<Biome> darkIslands;
    private final Holder<Biome> lightIslands;

    private final SolarisDensityFunction densityFunction;

    public static SolarisBiomeSource create(HolderGetter<Biome> getter) {
        return new SolarisBiomeSource(
                getter.getOrThrow(BiomeRegistry.SCORCHED_PLAINS),
                getter.getOrThrow(BiomeRegistry.WATER_ISLANDS),
                getter.getOrThrow(BiomeRegistry.FIRE_ISLANDS),
                getter.getOrThrow(BiomeRegistry.NATURE_ISLANDS),
                getter.getOrThrow(BiomeRegistry.LIGHTNING_ISLANDS),
                getter.getOrThrow(BiomeRegistry.DARK_ISLANDS),
                getter.getOrThrow(BiomeRegistry.LIGHT_ISLANDS));
    }

    private SolarisBiomeSource(Holder<Biome> scorchedPlains, Holder<Biome> waterIslands, Holder<Biome> fireIslands, Holder<Biome> natureIslands, Holder<Biome> lightningIslands, Holder<Biome> darkIslands, Holder<Biome> lightIslands) {
        this.scorchedPlains = scorchedPlains;
        this.waterIslands = waterIslands;
        this.fireIslands = fireIslands;
        this.natureIslands = natureIslands;
        this.lightningIslands = lightningIslands;
        this.darkIslands = darkIslands;
        this.lightIslands = lightIslands;
        this.densityFunction = new SolarisDensityFunction(0L);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(scorchedPlains, waterIslands, fireIslands, natureIslands, lightningIslands, darkIslands, lightIslands);
    }

    @Override
    public Holder<Biome> getNoiseBiome(int pX, int pY, int pZ, Climate.Sampler pSampler) {
        int i = QuartPos.toBlock(pX);
        int k = QuartPos.toBlock(pZ);
        Vector2i islandPos = densityFunction.biomeCompute(new DensityFunction.SinglePointContext(i, 0, k));
        float angle = (float) Math.atan2(islandPos.y, islandPos.x) + Mth.PI;
        if(angle >= 0 && angle <= Mth.PI/3) {
            return waterIslands;
        }
        if(angle > Mth.PI/3 && angle <= 2*Mth.PI/3) {
            return fireIslands;
        }
        if(angle > 2*Mth.PI/3 && angle <= Mth.PI) {
            return natureIslands;
        }
        if(angle > Mth.PI && angle <= 4*Mth.PI/3) {
            return lightningIslands;
        }
        if(angle > 4*Mth.PI/3 && angle <= 5*Mth.PI/3) {
            return darkIslands;
        }
        if(angle > 5*Mth.PI/3 && angle <= 2*Mth.PI) {
            return lightIslands;
        }
        return scorchedPlains;
    }
}
