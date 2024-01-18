package com.github.x3r.solaris.mixin;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.worldgen.dimension.SolarisDensityFunction;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DensityFunctions.class)
public abstract class DensityFunctionsMixin {
    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void bootstrap(Registry<Codec<? extends DensityFunction>> pRegistry, CallbackInfoReturnable<Codec<? extends DensityFunction>> cir) {
        Registry.register(pRegistry, new ResourceLocation(Solaris.MOD_ID, "solaris_density"), SolarisDensityFunction.CODEC.codec());
    }
}
