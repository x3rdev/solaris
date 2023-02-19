package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.biome.SolarisBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BiomeRegistry {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(Registry.BIOME_REGISTRY, Solaris.MOD_ID);

    public static final RegistryObject<Biome> SOLARIS_MIDLANDS = BIOMES.register("solaris_midlands", SolarisBiomes::solarisMidlands);

    public static final ResourceKey<Biome> SOLARIS_MIDLANDS_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Solaris.MOD_ID, "solaris_midlands"));
}
