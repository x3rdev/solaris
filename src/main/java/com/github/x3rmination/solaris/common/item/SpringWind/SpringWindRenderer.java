package com.github.x3rmination.solaris.common.item.SpringWind;

import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SpringWindRenderer extends GeoItemRenderer {
    public SpringWindRenderer() {
        super(new SpringWindModel());
    }

    @Override
    public void render(Item animatable, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn, ItemStack itemStack) {
        Minecraft.getInstance().getItemRenderer().renderStatic(
                new ItemStack(ItemRegistry.SPRING_WIND_HANDLE.get(), 1),
                ItemTransforms.TransformType.NONE,
                packedLightIn,
                OverlayTexture.NO_OVERLAY,
                stack,
                bufferIn,
                0);
        CompoundTag renderTag = itemStack.getTag();
        if(renderTag != null && !renderTag.getBoolean("active")) {
            Minecraft.getInstance().getItemRenderer().renderStatic(
                    new ItemStack(ItemRegistry.SPRING_WIND_BLADE.get(), 1),
                    ItemTransforms.TransformType.NONE,
                    packedLightIn,
                    OverlayTexture.NO_OVERLAY,
                    stack,
                    bufferIn,
                    0);
        }
    }
}
