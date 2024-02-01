package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class BiomeRegistry {

    public static final ResourceKey<Biome> SCORCHED_PLAINS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "scorched_plains"));
    public static final ResourceKey<Biome> SWAMP_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "swamp_islands"));
    public static final ResourceKey<Biome> ICE_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "ice_islands"));
    public static final ResourceKey<Biome> BEACH_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "beach_islands"));
    public static final ResourceKey<Biome> FIRE_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "fire_islands"));
    public static final ResourceKey<Biome> NATURE_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "nature_islands"));
    public static final ResourceKey<Biome> LIGHTNING_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "lightning_islands"));
    public static final ResourceKey<Biome> DARK_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "dark_islands"));
    public static final ResourceKey<Biome> LIGHT_ISLANDS = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "light_islands"));
    public static final ResourceKey<Biome> SOLARIS_VOID = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Solaris.MOD_ID, "solaris_void"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(SCORCHED_PLAINS, scorchedPlains(context));

        context.register(SWAMP_ISLANDS, waterIslands(context));
        context.register(ICE_ISLANDS, waterIslands(context));
        context.register(BEACH_ISLANDS, waterIslands(context));

        context.register(FIRE_ISLANDS, fireIslands(context));

        context.register(NATURE_ISLANDS, natureIslands(context));

        context.register(LIGHTNING_ISLANDS, testBiome(context));
        context.register(DARK_ISLANDS, testBiome(context));
        context.register(LIGHT_ISLANDS, testBiome(context));

        context.register(SOLARIS_VOID, solarisVoid(context));
    }

    private static Biome scorchedPlains(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder =
                new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityRegistry.SCORCHED_BUG.get(), 1, 2, 5));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(13866561)
                        .waterFogColor(14403179)
                        .skyColor(15587356)
                        .grassColorOverride(14063159)
                        .foliageColorOverride(14063159)
                        .fogColor(13854489)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.05F)).build())
                .build();
    }

    private static Biome waterIslands(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(2.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x0088ff)
                        .waterFogColor(0x188df2)
                        .skyColor(0x8bc1f0)
                        .grassColorOverride(0x507859)
                        .foliageColorOverride(0x507859)
                        .fogColor(0x99b7c4)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }

    private static Biome fireIslands(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xf58a42)
                        .waterFogColor(0xf58a42)
                        .skyColor(0xf57e42)
                        .grassColorOverride(0xde5331)
                        .foliageColorOverride(0xde5331)
                        .fogColor(0xde8831)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }

    private static Biome natureIslands(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3480ba)
                        .waterFogColor(0x3480ba)
                        .skyColor(0x7fbceb)
                        .grassColorOverride(0x54cf3a)
                        .foliageColorOverride(0x54cf3a)
                        .fogColor(0x82d96f)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }

    private static Biome solarisVoid(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x000000)
                        .waterFogColor(0x000000)
                        .skyColor(15587356)
                        .grassColorOverride(0x000000)
                        .foliageColorOverride(0x000000)
                        .fogColor(13854489)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }

    private static Biome testBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }
}
