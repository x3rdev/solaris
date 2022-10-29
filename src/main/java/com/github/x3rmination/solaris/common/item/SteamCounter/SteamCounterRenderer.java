package com.github.x3rmination.solaris.common.item.SteamCounter;

import com.github.x3rmination.solaris.common.item.SteamCounter.blade.SteamCounterBladeRenderer;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SteamCounterRenderer extends GeoItemRenderer {
    public SteamCounterRenderer() {
        super(new SteamCounterModel());
    }

    @Override
    public void render(Item animatable, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn, ItemStack itemStack) {
        SteamCounterBladeRenderer bladeRenderer = new SteamCounterBladeRenderer();
        bladeRenderer.render(ItemRegistry.STEAM_COUNTER_BLADE.get(), stack, bufferIn, packedLightIn, new ItemStack(ItemRegistry.STEAM_COUNTER_BLADE.get()));
        super.render(animatable, stack, bufferIn, packedLightIn, itemStack);
    }
}
