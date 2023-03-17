package com.github.x3rmination.solaris.common.item.CloudSplitter;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.PhoenixSpear.PhoenixSpearItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CloudSplitterModel extends AnimatedGeoModel<CloudSplitterItem> {


    @Override
    public ResourceLocation getModelLocation(CloudSplitterItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/cloud_splitter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CloudSplitterItem object) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/item/cloud_splitter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CloudSplitterItem animatable) {
        return null;
    }
}
