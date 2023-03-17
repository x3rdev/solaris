package com.github.x3rmination.solaris.common.item.CloudSplitter;

import com.github.x3rmination.solaris.common.item.PhoenixSpear.PhoenixSpearItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CloudSplitterRenderer extends GeoItemRenderer<CloudSplitterItem> {

    public CloudSplitterRenderer() {
        super(new CloudSplitterModel());
    }
}
