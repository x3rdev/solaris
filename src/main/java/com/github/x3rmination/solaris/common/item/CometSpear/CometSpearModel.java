package com.github.x3rmination.solaris.common.item.CometSpear;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CometSpearModel extends AnimatedGeoModel<CometSpearItem> {

    @Override
    public ResourceLocation getModelLocation(CometSpearItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/comet_spear.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CometSpearItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/comet_spear.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CometSpearItem animatable) {
        return null;
    }
}
