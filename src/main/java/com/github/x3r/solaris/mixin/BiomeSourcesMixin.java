package com.github.x3r.solaris.mixin;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.worldgen.biome.SolarisBiomeSource;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.BiomeSources;
import net.minecraft.world.level.biome.FixedBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeSources.class)
public abstract class BiomeSourcesMixin {
    @Inject(method = "bootstrap(Lnet/minecraft/core/Registry;)Lcom/mojang/serialization/Codec;", at = @At("HEAD"))
    private static void bootstrap(Registry<Codec<? extends BiomeSource>> pRegistry, CallbackInfoReturnable<Codec<? extends BiomeSource>> cir) {
        Registry.register(pRegistry, new ResourceLocation(Solaris.MOD_ID, "solaris"), SolarisBiomeSource.CODEC);
    }
}
