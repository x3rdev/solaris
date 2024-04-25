package com.github.x3r.solaris.client.renderer.block;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.block_entity.UrborosPolypBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class UrborosPolypRenderer extends GeoBlockRenderer<UrborosPolypBlockEntity> {

    public UrborosPolypRenderer() {
        super(new DefaultedBlockGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "urboros_polyp")));
    }
}
