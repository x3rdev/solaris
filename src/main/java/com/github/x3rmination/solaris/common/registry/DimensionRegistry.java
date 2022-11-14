package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class DimensionRegistry {

    public static final ResourceKey<Level> SOLARIS_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Solaris.MOD_ID, "solaris"));

    public static final ResourceKey<DimensionType> SOLARIS_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY,
            SOLARIS_KEY.getRegistryName());
}
