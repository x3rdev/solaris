package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.worldgen.biome.SolarisBiomeSource;
import com.github.x3r.solaris.common.worldgen.dimension.SolarisDensityFunction;
import com.github.x3r.solaris.common.worldgen.dimension.SolarisSurfaceRules;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
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

import java.util.List;
import java.util.OptionalLong;

public class DimensionRegistry {

    public static final ResourceKey<LevelStem> SOLARIS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Solaris.MOD_ID, "solaris"));
    public static final ResourceKey<Level> SOLARIS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Solaris.MOD_ID, "solaris"));
    public static final ResourceKey<DimensionType> SOLARIS_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Solaris.MOD_ID, "solaris_type"));

    public static final ResourceKey<LevelStem> TROLL_CAVE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Solaris.MOD_ID, "troll_cave"));
    public static final ResourceKey<Level> TROLL_CAVE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Solaris.MOD_ID, "troll_cave"));
    public static final ResourceKey<DimensionType> TROLL_CAVE_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Solaris.MOD_ID, "troll_cave_type"));

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
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0F,
                new DimensionType.MonsterSettings(false, false, ConstantInt.ZERO, 0)
        ));
        context.register(TROLL_CAVE_TYPE, new DimensionType(
                OptionalLong.of(0),
                false,
                false,
                false,
                false,
                1.0,
                false,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0F,
                new DimensionType.MonsterSettings(false, false, ConstantInt.ZERO, 0)
        ));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NormalNoise.NoiseParameters> noiseParameters = context.lookup(Registries.NOISE);

        NoiseBasedChunkGenerator solarisChunkGenerator = new NoiseBasedChunkGenerator(
                SolarisBiomeSource.create(biomeRegistry),
                Holder.direct(
                        new NoiseGeneratorSettings(
                            new NoiseSettings(0, 256, 1, 1),
                            Blocks.EMERALD_BLOCK.defaultBlockState(),
                            Blocks.AIR.defaultBlockState(),
                            router(noiseParameters),
                            ruleSource(),
                            List.of(),
                            0,
                            false,
                            false,
                            false,
                            false
                        )
                )
        );
        NoiseBasedChunkGenerator emptyChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.THE_VOID)),
                Holder.direct(
                        new NoiseGeneratorSettings(
                            new NoiseSettings(0, 256, 1, 1),
                            Blocks.AIR.defaultBlockState(),
                            Blocks.AIR.defaultBlockState(),
                            NoiseRouterData.none(),
                            SurfaceRules.state(Blocks.AIR.defaultBlockState()),
                            List.of(),
                            0,
                            false,
                            false,
                            false,
                            false
                        )
                )
        );

        LevelStem solarisStem = new LevelStem(dimTypes.getOrThrow(DimensionRegistry.SOLARIS_TYPE), solarisChunkGenerator);
        context.register(SOLARIS_KEY, solarisStem);
        LevelStem trollCaveStem = new LevelStem(dimTypes.getOrThrow(DimensionRegistry.TROLL_CAVE_TYPE), emptyChunkGenerator);
        context.register(TROLL_CAVE_KEY, trollCaveStem);
    }

    private static NoiseRouter router(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return new NoiseRouter(
                DensityFunctions.constant(1.0),
                solarisFluidLevelFloodednessFunction(noiseParameters),
                solarisFluidLevelSpreadFunction(noiseParameters),
                DensityFunctions.constant(-1.0),
                solarisTempFunction(noiseParameters),
                solarisVegetationFunction(noiseParameters),
                solarisContinentsFunction(),
                solarisErosionFunction(noiseParameters),
                solarisDepthFunction(noiseParameters),
                solarisRidgesFunction(noiseParameters),
                new SolarisDensityFunction(0L),
                new SolarisDensityFunction(0L),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0)
        );
    }

    private static DensityFunction solarisFluidLevelFloodednessFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 1, 1);
    }

    private static DensityFunction solarisFluidLevelSpreadFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 1, 1);
    }

    private static DensityFunction solarisTempFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.TEMPERATURE), 1, 1);
    }

    private static DensityFunction solarisVegetationFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.VEGETATION), 1, 1);
    }

    private static DensityFunction solarisContinentsFunction() {
        return new SolarisDensityFunction(0L);
    }

    private static DensityFunction solarisErosionFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.EROSION), 1, 1);
    }

    private static DensityFunction solarisDepthFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.GRAVEL), 1, 1);
    }

    private static DensityFunction solarisRidgesFunction(HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        return DensityFunctions.noise(noiseParameters.getOrThrow(Noises.GRAVEL), 1, 1);
    }

    private static SurfaceRules.RuleSource ruleSource() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.SCORCHED_PLAINS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SolarisSurfaceRules.topOfWorldCheck(5),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SolarisSurfaceRules.topOfWorldCheck(0),
                                                        SurfaceRules.state(BlockRegistry.SCORCHED_GRASS_BLOCK.get().defaultBlockState())
                                                ),
                                                SurfaceRules.state(BlockRegistry.SCORCHED_DIRT.get().defaultBlockState())
                                        )
                                ),
                                SurfaceRules.state(BlockRegistry.BRIMSTONE.get().defaultBlockState())
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.WATER_ISLANDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SolarisSurfaceRules.topOfWorldCheck(5),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SolarisSurfaceRules.topOfWorldCheck(0),
                                                        SurfaceRules.state(BlockRegistry.GRASSY_PERMAFROST.get().defaultBlockState())
                                                ),
                                                SurfaceRules.state(BlockRegistry.PERMAFROST.get().defaultBlockState())
                                        )
                                ),
                                SurfaceRules.state(BlockRegistry.SNOWY_STONE.get().defaultBlockState())
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.FIRE_ISLANDS),
                        SurfaceRules.state(Blocks.MAGMA_BLOCK.defaultBlockState())
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.NATURE_ISLANDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SolarisSurfaceRules.topOfWorldCheck(5),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SolarisSurfaceRules.topOfWorldCheck(0),
                                                        SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())
                                                ),
                                                SurfaceRules.state(Blocks.DIRT.defaultBlockState())
                                        )
                                ),
                                SurfaceRules.state(Blocks.STONE.defaultBlockState())
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.LIGHTNING_ISLANDS),
                        SurfaceRules.state(Blocks.COPPER_BLOCK.defaultBlockState())
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.DARK_ISLANDS),
                        SurfaceRules.state(Blocks.OBSIDIAN.defaultBlockState())
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.LIGHT_ISLANDS),
                        SurfaceRules.state(Blocks.GLOWSTONE.defaultBlockState())
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(BiomeRegistry.SOLARIS_VOID),
                        SurfaceRules.state(Blocks.AIR.defaultBlockState())
                )
        );
    }
}
