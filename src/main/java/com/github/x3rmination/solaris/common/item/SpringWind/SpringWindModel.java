package com.github.x3rmination.solaris.common.item.SpringWind;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpringWindModel extends AnimatedGeoModel<SpringWindItem> {

    @Override
    public ResourceLocation getModelLocation(SpringWindItem object) {
        return new ResourceLocation(Solaris.MOD_ID,"geo/spring_wind.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpringWindItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/abyssal_edge/abyssal_edge_0.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpringWindItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID,"animations/abyssal_edge_blade.animation.json");
    }
}
