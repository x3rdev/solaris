package com.github.x3rmination.solaris.common.item.LivingShield;

import com.github.x3rmination.solaris.common.item.PhoenixShield.PhoenixShieldItem;
import com.github.x3rmination.solaris.common.item.PhoenixShield.PhoenixShieldModel;
import com.github.x3rmination.solaris.common.item.PhoenixShield.PhoenixShieldOffhandModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class LivingShieldRenderer extends GeoItemRenderer<LivingShieldItem> {
    public LivingShieldRenderer() {
        super(new LivingShieldModel());
    }
}
