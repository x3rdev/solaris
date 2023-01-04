package com.github.x3rmination.solaris.common.item.SolarArmor;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SolarArmorModel extends AnimatedGeoModel<SolarArmorItem> {
    @Override
    public ResourceLocation getModelLocation(SolarArmorItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/solar_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SolarArmorItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/armor/solar_armor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SolarArmorItem animatable) {
        return new ResourceLocation(Solaris.MOD_ID, "animations/solar_armor.animation.json");
    }
}
