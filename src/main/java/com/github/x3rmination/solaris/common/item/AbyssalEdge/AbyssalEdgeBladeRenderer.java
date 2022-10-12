package com.github.x3rmination.solaris.common.item.AbyssalEdge;

import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AbyssalEdgeBladeRenderer extends GeoItemRenderer {

    public AbyssalEdgeBladeRenderer() {
        super(new AbyssalEdgeBladeModel());
    }

    @Override
    public void render(Item animatable, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn, ItemStack itemStack) {
        Minecraft.getInstance().getItemRenderer().renderStatic(
                new ItemStack(ItemRegistry.ABYSSAL_EDGE_HANDLE.get(), 1),
                ItemTransforms.TransformType.NONE,
                packedLightIn,
                OverlayTexture.NO_OVERLAY,
                stack,
                bufferIn,
                0);
        super.render(animatable, stack, bufferIn, packedLightIn, itemStack);
    }

    @Override
    public RenderType getRenderType(Object animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.endPortal();
    }
}
