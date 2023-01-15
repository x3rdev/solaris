package com.github.x3rmination.solaris.common.block;

import com.github.x3rmination.solaris.common.registry.BlockItemRegistry;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.MixinEnvironment;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SolarisSunBlockRenderer extends GeoBlockRenderer<SolarisSunBlockEntity> {

    public SolarisSunBlockRenderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new SolarisSunBlockModel());
    }

    @Override
    public void render(GeoModel model, SolarisSunBlockEntity animatable, float partialTick, RenderType type, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.translate(0, 0.5 - (1/128F), 0);
        poseStack.pushPose();
        poseStack.translate(0, -17, 0);
        poseStack.scale(140, 140, 140);
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        Minecraft.getInstance().getItemRenderer().renderStatic(BlockItemRegistry.SOLARIS_SUN.get().getDefaultInstance(), ItemTransforms.TransformType.GROUND, 15728880, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, 0);
        poseStack.popPose();
        poseStack.scale(6.1F, 6.1F, 6.1F);
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
