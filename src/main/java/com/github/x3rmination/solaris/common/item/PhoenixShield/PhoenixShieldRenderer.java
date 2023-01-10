package com.github.x3rmination.solaris.common.item.PhoenixShield;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PhoenixShieldRenderer extends GeoItemRenderer<PhoenixShieldItem> {
    public PhoenixShieldRenderer() {
        super(new PhoenixShieldModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        if(transformType.equals(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND) || transformType.equals(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)) {
//            poseStack.scale(-1, 1, 1);
//            poseStack.translate(-1, 0, 0);
//        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
