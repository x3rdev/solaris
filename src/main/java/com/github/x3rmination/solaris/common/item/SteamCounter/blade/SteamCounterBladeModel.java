package com.github.x3rmination.solaris.common.item.SteamCounter.blade;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.RenderItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SteamCounterBladeModel extends AnimatedGeoModel<SteamCounterBladeItem> {

    @Override
    public ResourceLocation getModelLocation(SteamCounterBladeItem object) {
        return new ResourceLocation(Solaris.MOD_ID,"geo/steam_counter_blade.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SteamCounterBladeItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/steam_counter/blade0.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SteamCounterBladeItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID,"animations/steam_counter.animation.json");
    }
}
