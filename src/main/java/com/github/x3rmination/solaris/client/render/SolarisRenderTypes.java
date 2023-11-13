package com.github.x3rmination.solaris.client.render;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.client.ClientSetup;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class SolarisRenderTypes {

    public static RenderType abyssalEdge(ResourceLocation location) {
        return Internal.ABYSSAL_EDGE.apply(location);
    }

    private static class Internal extends RenderType {

        public Internal(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
            super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
        }

        private static final Function<ResourceLocation, RenderType> ABYSSAL_EDGE = Util.memoize((location) -> {
            RenderType.CompositeState renderState = RenderType.CompositeState.builder()
                    .setShaderState(new ShaderStateShard(ClientSetup::getAbyssalBladeShader))
                    .setTextureState(new RenderStateShard.TextureStateShard(location, false, false))
                    .setTransparencyState(NO_TRANSPARENCY)
                    .setLightmapState(LIGHTMAP)
                    .setOverlayState(OVERLAY)
                    .createCompositeState(true);
            return create("abyssal_edge", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, false, renderState);
        });
    }
}
