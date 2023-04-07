package com.github.x3rmination.solaris.common.item.FireKatana;

import com.github.x3rmination.solaris.client.render.SolarisRenderTypes;
import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;
import software.bernie.geckolib3.util.RenderUtils;

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
