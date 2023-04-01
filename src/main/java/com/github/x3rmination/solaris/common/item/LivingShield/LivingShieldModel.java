package com.github.x3rmination.solaris.common.item.LivingShield;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.PhoenixShield.PhoenixShieldItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LivingShieldModel extends AnimatedGeoModel<LivingShieldItem> {

    @Override
    public ResourceLocation getModelLocation(LivingShieldItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/living_shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LivingShieldItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/living_shield.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LivingShieldItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID, "animations/living_shield.animation.json");
    }
}
