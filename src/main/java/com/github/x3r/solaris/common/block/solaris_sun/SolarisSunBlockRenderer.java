package com.github.x3r.solaris.common.block.solaris_sun;

import com.github.x3r.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SolarisSunBlockRenderer extends GeoBlockRenderer<SolarisSunBlockEntity> {

    public SolarisSunBlockRenderer() {
        super(new SolarisSunBlockModel());
    }

    @Override
    public void actuallyRender(PoseStack poseStack, SolarisSunBlockEntity animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.translate(0, 0.5 - (1/128F), 0);

        poseStack.pushPose();
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(animatable.time + partialTick % 180));
        poseStack.translate(0, -17, 0);
        poseStack.scale(150, 150, 150);
        Minecraft.getInstance().getItemRenderer().renderStatic(ItemRegistry.SOLARIS_SUN.get().getDefaultInstance(), ItemDisplayContext.GROUND, 15728880, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, animatable.getLevel(), 0);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees((animatable.time + partialTick % 180) * -1));
        poseStack.translate(0, -17, 0);
        poseStack.scale(140, 140, 140);
        Minecraft.getInstance().getItemRenderer().renderStatic(ItemRegistry.SOLARIS_SUN_AURA.get().getDefaultInstance(), ItemDisplayContext.GROUND, 15728880, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, animatable.getLevel(), 0);
        poseStack.popPose();

        poseStack.scale(6F, 6F, 6F);
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, 15728880, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public int getViewDistance() {
        return 10000;
    }
}
