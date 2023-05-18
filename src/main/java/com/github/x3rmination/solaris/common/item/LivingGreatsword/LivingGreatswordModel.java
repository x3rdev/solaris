package com.github.x3rmination.solaris.common.item.LivingGreatsword;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LivingGreatswordModel extends AnimatedGeoModel<LivingGreatswordItem> {

    @Override
    public ResourceLocation getModelLocation(LivingGreatswordItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/living_greatsword.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LivingGreatswordItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/living_greatsword.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LivingGreatswordItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID, "animations/living_greatsword.animation.json");
    }
}
