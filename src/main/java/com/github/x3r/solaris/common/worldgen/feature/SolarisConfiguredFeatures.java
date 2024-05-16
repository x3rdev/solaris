package com.github.x3r.solaris.common.worldgen.feature;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.registry.BlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class SolarisConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SCORCHED_IRON_ORE = registerKey("scorched_iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCORCHED_TREE = registerKey("scorched_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCORCHED_GRASS = registerKey("scorched_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWY_GRASS = registerKey("snowy_grass");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest brimstoneReplaceable = new BlockMatchTest(BlockRegistry.BRIMSTONE.get());

        register(context, SCORCHED_IRON_ORE, Feature.ORE, new OreConfiguration(
                brimstoneReplaceable,
                BlockRegistry.SCORCHED_IRON_ORE.get().defaultBlockState(),
                7));
        register(context, SCORCHED_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.SCORCHED_LOG.get()),
                new ForkingTrunkPlacer(7, 3, 1),
                BlockStateProvider.simple(Blocks.AIR),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(BlockRegistry.SCORCHED_DIRT.get())).build());
        register(context, SCORCHED_GRASS, Feature.RANDOM_PATCH, VegetationFeatures.grassPatch(
                BlockStateProvider.simple(BlockRegistry.SCORCHED_GRASS.get()), 32));
        register(context, SNOWY_GRASS, Feature.RANDOM_PATCH, VegetationFeatures.grassPatch(
                BlockStateProvider.simple(BlockRegistry.SNOWY_GRASS.get()), 32));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Solaris.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
