package com.github.x3r.solaris.client.renderer.entity;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.UrborosEntity;
import com.github.x3r.solaris.common.entity.UrborosEphyraEntity;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.cache.texture.AnimatableTexture;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class UrborosEphyraRenderer extends GeoEntityRenderer<UrborosEphyraEntity> {

    public UrborosEphyraRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "urboros_ephyra")));
    }
}
