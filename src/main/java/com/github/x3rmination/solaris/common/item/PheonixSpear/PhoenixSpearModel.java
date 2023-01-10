package com.github.x3rmination.solaris.common.item.PheonixSpear;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;

public class PhoenixSpearModel extends AnimatedGeoModel<PhoenixSpearItem> {

    @Override
    public ResourceLocation getModelLocation(PhoenixSpearItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/phoenix_spear.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixSpearItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/phoenix_spear.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PhoenixSpearItem animatable) {
        return null;
    }
}
