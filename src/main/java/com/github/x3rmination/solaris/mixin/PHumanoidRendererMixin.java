package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.client.body_parts.BodyPartLayer;
import com.github.x3rmination.solaris.client.body_parts.PatchedBodyPartLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;
import yesman.epicfight.client.renderer.patched.layer.PatchedLayer;

@Mixin(PHumanoidRenderer.class)
public abstract class PHumanoidRendererMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        ((PHumanoidRenderer) ((Object) this)).addPatchedLayer(BodyPartLayer.class, new PatchedBodyPartLayer<>());
    }
}
