package com.github.x3r.solaris.common.worldgen.biome;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.registry.BiomeRegistry;
import com.github.x3r.solaris.common.registry.EntityRegistry;
import com.github.x3r.solaris.common.worldgen.feature.SolarisPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SolarisBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_SCORCHED_IRON_ORE = registerKey("add_scorched_iron_ore");
    public static final ResourceKey<BiomeModifier> ADD_SCORCHED_TREE = registerKey("add_scorched_tree");
    public static final ResourceKey<BiomeModifier> ADD_SCORCHED_GRASS = registerKey("add_scorched_grass");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SCORCHED_IRON_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(BiomeRegistry.SCORCHED_PLAINS)),
                HolderSet.direct(placedFeatures.getOrThrow(SolarisPlacedFeatures.SCORCHED_IRON_ORE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SCORCHED_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(BiomeRegistry.SCORCHED_PLAINS)),
                HolderSet.direct(placedFeatures.getOrThrow(SolarisPlacedFeatures.SCORCHED_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_SCORCHED_GRASS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(BiomeRegistry.SCORCHED_PLAINS)),
                HolderSet.direct(placedFeatures.getOrThrow(SolarisPlacedFeatures.SCORCHED_GRASS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Solaris.MOD_ID, name));
    }
}
