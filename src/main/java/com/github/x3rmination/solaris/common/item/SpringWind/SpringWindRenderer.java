package com.github.x3rmination.solaris.common.item.SpringWind;

import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SpringWindRenderer extends GeoItemRenderer<SpringWindItem> {
    public SpringWindRenderer() {
        super(new SpringWindModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        CompoundTag renderTag = stack.getTag();
        GeoModel model = modelProvider.getModel(modelProvider.getModelLocation(((SpringWindItem) stack.getItem())));
        if(renderTag != null && renderTag.getBoolean("active")) {
            model.getBone("blade").ifPresent(geoBone -> geoBone.setHidden(true));
        } else {
            model.getBone("blade").ifPresent(geoBone -> geoBone.setHidden(false));
        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
