package com.github.x3rmination.solaris.common.item.PhoenixShield;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import yesman.epicfight.client.ClientEngine;

public class PhoenixShieldRenderer extends GeoItemRenderer<PhoenixShieldItem> {

    private PhoenixShieldModel mainHandModel;
    private PhoenixShieldOffhandModel offHandModel;
    public PhoenixShieldRenderer() {
        super(new PhoenixShieldModel());
        mainHandModel = new PhoenixShieldModel();
        offHandModel = new PhoenixShieldOffhandModel();
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        boolean left = transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND;
        if(!left) {
            setModel(mainHandModel);
        } else {
            setModel(offHandModel);
            if(ClientEngine.instance.controllEngine.getPlayerPatch().isBattleMode()) {
                poseStack.translate(1, 0, 0);
            }
        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
