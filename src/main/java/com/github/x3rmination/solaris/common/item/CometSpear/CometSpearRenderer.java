package com.github.x3rmination.solaris.common.item.CometSpear;

import com.github.x3rmination.solaris.common.item.PhoenixSpear.PhoenixSpearItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CometSpearRenderer extends GeoItemRenderer<CometSpearItem> {
    public CometSpearRenderer() {
        super(new CometSpearModel());
    }
}
