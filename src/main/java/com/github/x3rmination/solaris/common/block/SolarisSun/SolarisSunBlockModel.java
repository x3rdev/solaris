package com.github.x3rmination.solaris.common.block.SolarisSun;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SolarisSunBlockModel extends AnimatedGeoModel<SolarisSunBlockEntity> {

    @Override
    public ResourceLocation getModelLocation(SolarisSunBlockEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/solaris_sun.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SolarisSunBlockEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/block/solaris_sun.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SolarisSunBlockEntity animatable) {
        return new ResourceLocation(Solaris.MOD_ID, "animations/solaris_sun.animation.json");
    }
}
