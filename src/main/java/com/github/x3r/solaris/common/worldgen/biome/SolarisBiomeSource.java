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
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.DensityFunction;

import java.util.stream.Stream;

public class SolarisBiomeSource extends BiomeSource {

    public static final Codec<SolarisBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                RegistryOps.retrieveElement(BiomeRegistry.SCORCHED_PLAINS),
                RegistryOps.retrieveElement(BiomeRegistry.SWAMP_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.ICE_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.BEACH_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.FIRE_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.NATURE_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.LIGHTNING_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.DARK_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.LIGHT_ISLANDS),
                RegistryOps.retrieveElement(BiomeRegistry.SOLARIS_VOID)
        ).apply(instance, instance.stable(SolarisBiomeSource::new));
    });
    private final Holder<Biome> scorchedPlains;
    private final Holder<Biome> swampIslands;
    private final Holder<Biome> iceIslands;
    private final Holder<Biome> beachIslands;
    private final Holder<Biome> fireIslands;
    private final Holder<Biome> natureIslands;
    private final Holder<Biome> lightningIslands;
    private final Holder<Biome> darkIslands;
    private final Holder<Biome> lightIslands;
    private final Holder<Biome> voidBiome;

    private final SolarisDensityFunction densityFunction;

    public static SolarisBiomeSource create(HolderGetter<Biome> getter) {
        return new SolarisBiomeSource(
                getter.getOrThrow(BiomeRegistry.SCORCHED_PLAINS),
                getter.getOrThrow(BiomeRegistry.SWAMP_ISLANDS),
                getter.getOrThrow(BiomeRegistry.ICE_ISLANDS),
                getter.getOrThrow(BiomeRegistry.BEACH_ISLANDS),
                getter.getOrThrow(BiomeRegistry.FIRE_ISLANDS),
                getter.getOrThrow(BiomeRegistry.NATURE_ISLANDS),
                getter.getOrThrow(BiomeRegistry.LIGHTNING_ISLANDS),
                getter.getOrThrow(BiomeRegistry.DARK_ISLANDS),
                getter.getOrThrow(BiomeRegistry.LIGHT_ISLANDS),
                getter.getOrThrow(BiomeRegistry.SOLARIS_VOID));
    }

    private SolarisBiomeSource(Holder<Biome> scorchedPlains, Holder<Biome> swampIslands, Holder<Biome> iceIslands, Holder<Biome> beachIslands,Holder<Biome> fireIslands, Holder<Biome> natureIslands, Holder<Biome> lightningIslands, Holder<Biome> darkIslands, Holder<Biome> lightIslands, Holder<Biome> voidBiome) {
        this.scorchedPlains = scorchedPlains;
        this.swampIslands = swampIslands;
        this.iceIslands = iceIslands;
        this.beachIslands = beachIslands;
        this.fireIslands = fireIslands;
        this.natureIslands = natureIslands;
        this.lightningIslands = lightningIslands;
        this.darkIslands = darkIslands;
        this.lightIslands = lightIslands;
        this.voidBiome = voidBiome;
        this.densityFunction = new SolarisDensityFunction(0L);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(scorchedPlains, swampIslands, iceIslands, beachIslands, fireIslands, natureIslands, lightningIslands, darkIslands, lightIslands);
    }

    @Override
    public Holder<Biome> getNoiseBiome(int pX, int pY, int pZ, Climate.Sampler pSampler) {
        int i = QuartPos.toBlock(pX);
        int k = QuartPos.toBlock(pZ);
        int i1 = SectionPos.blockToSectionCoord(i);
        int k1 = SectionPos.blockToSectionCoord(k);
        long dist = (long)i1 * (long)i1 + (long)k1 * (long)k1;
//        double biomeCompute = densityFunction.biomeCompute(i, k);
//        if(biomeCompute > 0) {
//            if (dist < 200) {
//                return scorchedPlains;
//            }
//            double subBiomeCompute = densityFunction.subBiomeCompute(i, k);
//            if (dist < 2000) {
//                switch ((int) Math.round(1+(biomeCompute*3))) {
//                    case 1: {
//                        return switch ((int) Math.round(subBiomeCompute * 2)) {
//                            case 0 -> swampIslands;
//                            case 1 -> iceIslands;
//                            default -> beachIslands;
//                        };
//                    }
//                    case 2: return fireIslands;
//                    case 3: return natureIslands;
//                    case 4: return lightningIslands;
//                    default: return lightIslands;
//                }
//            }
//            if (dist > 2500) {
//                return darkIslands;
//            }
//        }
//        return voidBiome;
        return scorchedPlains;
    }
}
