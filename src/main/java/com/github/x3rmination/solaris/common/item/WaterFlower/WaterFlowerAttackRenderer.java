package com.github.x3rmination.solaris.common.item.WaterFlower;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class WaterFlowerAttackRenderer extends GeoProjectilesRenderer<WaterFlowerAttackEntity> {


    public WaterFlowerAttackRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WaterFlowerAttackModel());
    }

    @Override
    public void render(WaterFlowerAttackEntity animatable, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(7, 7, 7);
        super.render(animatable, yaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public RenderType getRenderType(WaterFlowerAttackEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}
