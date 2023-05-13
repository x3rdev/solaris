package com.github.x3rmination.solaris.common.item.LivingShield;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class LivingShieldRenderer extends GeoItemRenderer<LivingShieldItem> {
    public LivingShieldRenderer() {
        super(new LivingShieldModel());
    }
}
