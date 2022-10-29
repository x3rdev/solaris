package com.github.x3rmination.solaris.common.item.SteamCounter;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.AbyssalEdge.AbyssalEdgeBladeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SteamCounterModel extends AnimatedGeoModel<SteamCounterItem> {
    @Override
    public ResourceLocation getModelLocation(SteamCounterItem object) {
        return new ResourceLocation(Solaris.MOD_ID,"geo/steam_counter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SteamCounterItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/steam_counter/main.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SteamCounterItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID,"animations/steam_counter.animation.json");
    }
}
