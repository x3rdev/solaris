package com.github.x3rmination.solaris.common.biome;

import net.minecraft.data.worldgen.Carvers;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class SolarisBiomes {

    private static Biome baseSolarisBiome(BiomeGenerationSettings.Builder pGenerationSettings) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.THEEND).temperature(0.5F).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(pGenerationSettings.build()).build();
    }

    public static Biome solarisMidlands() {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder();
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE);
        return baseSolarisBiome(builder);
    }
}
