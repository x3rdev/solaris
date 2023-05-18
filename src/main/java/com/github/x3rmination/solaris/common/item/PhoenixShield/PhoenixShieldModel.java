package com.github.x3rmination.solaris.common.item.PhoenixShield;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PhoenixShieldModel extends AnimatedGeoModel<PhoenixShieldItem> {
    @Override
    public ResourceLocation getModelLocation(PhoenixShieldItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/phoenix_shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixShieldItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/phoenix_shield.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PhoenixShieldItem animatable) {
        return null;
    }
}
