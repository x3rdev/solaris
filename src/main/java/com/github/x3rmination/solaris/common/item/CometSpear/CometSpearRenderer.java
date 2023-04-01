package com.github.x3rmination.solaris.common.item.CometSpear;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import yesman.epicfight.client.ClientEngine;

public class CometSpearRenderer extends GeoItemRenderer<CometSpearItem> {
    public CometSpearRenderer() {
        super(new CometSpearModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if(ClientEngine.instance.controllEngine.getPlayerPatch().isBattleMode() && (transformType.equals(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND) || transformType.equals(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND))) {
            poseStack.translate(0, 0.3, -0.125);
        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
