package com.github.x3rmination.solaris.common.item.TestDemon;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestDemonModel extends AnimatedGeoModel<TestDemonItem> {
    @Override
    public ResourceLocation getModelLocation(TestDemonItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/test_demon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TestDemonItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/test_demon.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TestDemonItem animatable) {
        return null;
    }
}
