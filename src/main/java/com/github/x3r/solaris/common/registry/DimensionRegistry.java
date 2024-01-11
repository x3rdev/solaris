package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.worldgen.biome.SolarisBiomeSource;
import com.github.x3r.solaris.common.worldgen.dimension.SolarisDensityFunction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

public class DimensionRegistry {

    public static final ResourceKey<LevelStem> SOLARIS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Solaris.MOD_ID, "solaris"));
    public static final ResourceKey<Level> SOLARIS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Solaris.MOD_ID, "solaris"));
    public static final ResourceKey<DimensionType> SOLARIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Solaris.MOD_ID, "solaris_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(SOLARIS_TYPE, new DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                false,
                1.0,
                false,
                false,
                -64,
                320,
                320,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0F,
                new DimensionType.MonsterSettings(false, false, ConstantInt.ZERO, 0)
        ));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                SolarisBiomeSource.create(biomeRegistry),
                Holder.direct(
                        new NoiseGeneratorSettings(
                            new NoiseSettings(0, 128, 1, 1),
                            Blocks.EMERALD_BLOCK.defaultBlockState(),
                            Blocks.AIR.defaultBlockState(),
                            router(),
                            ruleSource(),
                            List.of(),
                            0,
                            true,
                            false,
                            false,
                            false
                        )
                )
        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(DimensionRegistry.SOLARIS_TYPE), noiseBasedChunkGenerator);
        context.register(SOLARIS_KEY, stem);
    }

    private static NoiseRouter router() {
        return new NoiseRouter(
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                new SolarisDensityFunction(0L),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0)
        );
    }

    private static SurfaceRules.RuleSource ruleSource() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.SCORCHED_PLAINS), SurfaceRules.state(BlockRegistry.BRIMSTONE.get().defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.WATER_ISLANDS), SurfaceRules.state(Blocks.WET_SPONGE.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.FIRE_ISLANDS), SurfaceRules.state(Blocks.MAGMA_BLOCK.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.NATURE_ISLANDS), SurfaceRules.state(Blocks.MOSSY_COBBLESTONE.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.LIGHTNING_ISLANDS), SurfaceRules.state(Blocks.COPPER_BLOCK.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.DARK_ISLANDS), SurfaceRules.state(Blocks.OBSIDIAN.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.LIGHT_ISLANDS), SurfaceRules.state(Blocks.GLOWSTONE.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.SOLARIS_VOID), SurfaceRules.state(Blocks.AIR.defaultBlockState()))
        );
    }
}
