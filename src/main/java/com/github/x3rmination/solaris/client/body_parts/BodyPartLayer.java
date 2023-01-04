package com.github.x3rmination.solaris.client.body_parts;

import com.github.x3rmination.solaris.Solaris;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class BodyPartLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public BodyPartLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer clientPlayer, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ModelPart part = TestModel.createBodyLayer().bakeRoot();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entitySolid(new ResourceLocation(Solaris.MOD_ID, "textures/body_part/texture.png")));
        HumanoidModel<AbstractClientPlayer> parentModel = this.getParentModel();
        part.copyFrom(parentModel.getHead());
        part.render(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(clientPlayer, 0.0F));
    }
}
