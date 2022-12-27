package com.github.x3rmination.solaris.common.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SolarisSunBlockRenderer extends GeoBlockRenderer<SolarisSunBlockEntity> {

    public SolarisSunBlockRenderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new SolarisSunBlockModel());
    }

    @Override
    public void render(GeoModel model, SolarisSunBlockEntity animatable, float partialTick, RenderType type, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.translate(0, 0.5 - (1/128F), 0);
        poseStack.scale(2, 2, 2);
        super.render(model, animatable, partialTick, type, poseStack, bufferSource, buffer, 15728880, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public RenderType getRenderType(SolarisSunBlockEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public int getViewDistance() {
        return 10000;
    }
}
