package com.github.x3rmination.solaris.common.item.LivingShield;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import yesman.epicfight.client.ClientEngine;

public class LivingShieldRenderer extends GeoItemRenderer<LivingShieldItem> {
    public LivingShieldRenderer() {
        super(new LivingShieldModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        boolean left = transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND;
        if(left && ClientEngine.instance.controllEngine.getPlayerPatch().isBattleMode()) {
            poseStack.translate(1, 0, 0);
        }
    }
}
