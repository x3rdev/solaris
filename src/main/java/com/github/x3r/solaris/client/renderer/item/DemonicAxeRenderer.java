package com.github.x3r.solaris.client.renderer.item;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.item.DemonicAxe;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DemonicAxeRenderer extends GeoItemRenderer<DemonicAxe> {
    public DemonicAxeRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "demonic_axe")));
    }

    @Override
    public RenderType getRenderType(DemonicAxe animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
