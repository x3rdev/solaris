package com.github.x3r.solaris.common.datagen;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.registry.BiomeRegistry;
import com.github.x3r.solaris.common.registry.DimensionRegistry;
import com.github.x3r.solaris.common.worldgen.biome.SolarisBiomeModifiers;
import com.github.x3r.solaris.common.worldgen.feature.SolarisConfiguredFeatures;
import com.github.x3r.solaris.common.worldgen.feature.SolarisPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SolarisWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, DimensionRegistry::bootstrapType)
            .add(Registries.LEVEL_STEM, DimensionRegistry::bootstrapStem)
            .add(Registries.BIOME, BiomeRegistry::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, SolarisBiomeModifiers::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, SolarisConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, SolarisPlacedFeatures::bootstrap);
    public SolarisWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Solaris.MOD_ID));
    }
}
