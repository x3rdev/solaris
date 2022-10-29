package com.github.x3rmination.solaris.common.item.AbyssalEdge;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AbyssalEdgeAttackModel extends AnimatedGeoModel<AbyssalEdgeAttackEntity> {

    @Override
    public ResourceLocation getModelLocation(AbyssalEdgeAttackEntity object) {
        return new ResourceLocation(Solaris.MOD_ID,"geo/abyssal_edge_attack.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AbyssalEdgeAttackEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/abyssal_edge/abyssal_edge_0.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AbyssalEdgeAttackEntity animatable) {
        return new ResourceLocation(Solaris.MOD_ID,"animations/abyssal_edge_attack.animation.json");
    }
}
