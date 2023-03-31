package com.github.x3rmination.solaris.common.item.IceDragonSword;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IceDragonSwordModel extends AnimatedGeoModel<IceDragonSwordItem> {
    @Override
    public ResourceLocation getModelLocation(IceDragonSwordItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/ice_dragon_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(IceDragonSwordItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/ice_dragon_sword.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IceDragonSwordItem animatable) {
        return null;
    }
}
