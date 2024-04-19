package com.github.x3r.solaris.client.renderer.entity;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.model.entity.SnowTrollModel;
import com.github.x3r.solaris.common.entity.SnowTrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SnowTrollRenderer extends GeoEntityRenderer<SnowTrollEntity> {

    public SnowTrollRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SnowTrollModel());
    }

    @Override
    public int getPackedOverlay(SnowTrollEntity animatable, float u) {
        return OverlayTexture.pack(OverlayTexture.u(u),
                OverlayTexture.v(animatable.hurtTime > 0));
    }
    @Override
    protected float getDeathMaxRotation(SnowTrollEntity animatable) {
        return 0;
    }
}
