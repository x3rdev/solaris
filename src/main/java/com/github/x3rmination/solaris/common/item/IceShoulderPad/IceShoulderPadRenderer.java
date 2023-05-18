package com.github.x3rmination.solaris.common.item.IceShoulderPad;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class IceShoulderPadRenderer implements ICurioRenderer {

    public IceShoulderPadRenderer() {

    }
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack itemStack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch) {
        LivingEntity livingEntity = slotContext.entity();
//        BakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getItemModel(itemStack);
        ICurioRenderer.translateIfSneaking(poseStack, livingEntity);
        ICurioRenderer.rotateIfSneaking(poseStack, livingEntity);
        ICurioRenderer.followBodyRotations(slotContext.entity());
        poseStack.scale(1F, 1F, 1F);
        poseStack.translate(0.1F, 0.9F, 0F);
        poseStack.mulPose(Direction.DOWN.getRotation());
        Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemTransforms.TransformType.NONE, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, 0);
    }
}
