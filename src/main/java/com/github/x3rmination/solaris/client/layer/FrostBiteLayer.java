package com.github.x3rmination.solaris.client.layer;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FrostBiteLayer extends RenderLayer {

    private final LivingEntityRenderer renderer;
    private static final ResourceLocation FROZEN_TEXTURE = new ResourceLocation(Solaris.MOD_ID, "textures/entity/frostbite.png");

    public FrostBiteLayer(LivingEntityRenderer pRenderer) {
        super(pRenderer);
        this.renderer = pRenderer;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Entity entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if(entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffectRegistry.FROSTBITE.get())) {
            EntityModel model = this.renderer.getModel();
            float transparency = 1;
            VertexConsumer ivertexbuilder = pBuffer.getBuffer(RenderType.entityTranslucent(FROZEN_TEXTURE));
            model.renderToBuffer(pPoseStack, ivertexbuilder, pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, transparency);
        }
    }
}
