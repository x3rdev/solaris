package com.github.x3rmination.solaris.common.item.PhoenixShield;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import yesman.epicfight.client.ClientEngine;

public class PhoenixShieldRenderer extends GeoItemRenderer<PhoenixShieldItem> {
    public PhoenixShieldRenderer() {
        super(new PhoenixShieldModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        ClientEngine.instance.renderEngine.getEntityRenderer()
        if(transformType.equals(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)) {


        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
