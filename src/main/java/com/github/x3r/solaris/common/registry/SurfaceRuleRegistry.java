package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.worldgen.dimension.SolarisSurfaceRules;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SurfaceRuleRegistry {

    public static final DeferredRegister<Codec<? extends SurfaceRules.ConditionSource>> SURFACE_RULES = DeferredRegister.create(BuiltInRegistries.MATERIAL_CONDITION.key(), Solaris.MOD_ID);

    public static final RegistryObject<Codec<SolarisSurfaceRules.TopOfWorldSource>> TOP_OF_WORLD_SURFACE_RULE = SURFACE_RULES.register("",
            SolarisSurfaceRules.TopOfWorldSource.CODEC::codec);
}
