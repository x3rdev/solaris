package com.github.x3rmination.solaris.common.item.PhoenixShield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.commands.arguments.item.ItemPredicateArgument;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PhoenixShieldRenderer extends GeoItemRenderer<PhoenixShieldItem> {

    private ItemTransforms.TransformType transformType = ItemTransforms.TransformType.NONE;
    public PhoenixShieldRenderer() {
        super(new PhoenixShieldModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        this.transformType = transformType;
//        if(transformType.equals(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND) || transformType.equals(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)) {
//            GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(animatable));
//            model.getBone("all").ifPresent(geoBone -> {
//                geoBone.multiplyScale(-1, 1, 1);
//            });
//
//        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
    }

//    @Override
//    public void renderRecursively(GeoBone bone, PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//
//        if(this.transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
//            if(bone.getName().equals("feather")) {
//                bone.addPositionX(20);
//            }
//        }
//        super.renderRecursively(bone, poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//    }
}
