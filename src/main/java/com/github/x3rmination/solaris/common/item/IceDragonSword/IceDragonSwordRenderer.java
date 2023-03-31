package com.github.x3rmination.solaris.common.item.IceDragonSword;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class IceDragonSwordRenderer extends GeoItemRenderer<IceDragonSwordItem> {
    public IceDragonSwordRenderer() {
        super(new IceDragonSwordModel());
    }
}
