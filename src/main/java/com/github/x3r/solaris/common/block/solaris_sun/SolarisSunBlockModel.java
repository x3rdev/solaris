package com.github.x3r.solaris.common.block.solaris_sun;

import com.github.x3r.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SolarisSunBlockModel extends GeoModel<SolarisSunBlockEntity> {

    @Override
    public ResourceLocation getModelResource(SolarisSunBlockEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/solaris_sun.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SolarisSunBlockEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/block/solaris_sun.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SolarisSunBlockEntity animatable) {
        return new ResourceLocation(Solaris.MOD_ID, "animations/solaris_sun.animation.json");
    }
}
