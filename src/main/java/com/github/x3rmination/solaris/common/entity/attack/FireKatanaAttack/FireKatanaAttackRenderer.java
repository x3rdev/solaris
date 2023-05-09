package com.github.x3rmination.solaris.common.entity.attack.FireKatanaAttack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class FireKatanaAttackRenderer extends GeoProjectilesRenderer<FireKatanaAttackEntity> {

    public FireKatanaAttackRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FireKatanaAttackModel());
    }

    @Override
    public void render(FireKatanaAttackEntity animatable, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.translate(0, -2.5, 0);
        poseStack.scale(10, 10, 10);
        super.render(animatable, yaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public RenderType getRenderType(FireKatanaAttackEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }



}
