package com.github.x3rmination.solaris.common.item.AbyssalEdge;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AbyssalEdgeBladeModel extends AnimatedGeoModel<AbyssalEdgeBladeItem> {

    @Override
    public ResourceLocation getModelLocation(AbyssalEdgeBladeItem object) {
        return new ResourceLocation(Solaris.MOD_ID,"geo/abyssal_edge_blade.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AbyssalEdgeBladeItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "item/abyssal_edge/abyssal_edge_0");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AbyssalEdgeBladeItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID,"animations/abyssal_edge_blade.animation.json");
    }
}
