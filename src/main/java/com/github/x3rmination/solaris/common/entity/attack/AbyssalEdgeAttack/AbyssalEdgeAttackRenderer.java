package com.github.x3rmination.solaris.common.entity.attack.AbyssalEdgeAttack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class AbyssalEdgeAttackRenderer extends GeoProjectilesRenderer {
    public AbyssalEdgeAttackRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AbyssalEdgeAttackModel());
    }

    @Override
    public void render(Entity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public void renderLate(Object animatable, PoseStack stackIn, float partialTicks, MultiBufferSource renderTypeBuffer, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        stackIn.scale(30, 10, 1);
        super.renderLate(animatable, stackIn, partialTicks, renderTypeBuffer, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public RenderType getRenderType(Object animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.endPortal();
    }
}
