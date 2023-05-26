package com.github.x3rmination.solaris.common.item.LivingGlaive;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LivingGlaiveModel extends AnimatedGeoModel<LivingGlaiveItem> {

    @Override
    public ResourceLocation getModelLocation(LivingGlaiveItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/living_glaive.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LivingGlaiveItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/living_glaive.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LivingGlaiveItem animatable) {
        return null;
    }
}
