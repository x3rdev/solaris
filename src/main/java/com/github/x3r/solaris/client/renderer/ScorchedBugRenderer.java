package com.github.x3r.solaris.client.renderer;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.ScorchedBugEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ScorchedBugRenderer extends GeoEntityRenderer<ScorchedBugEntity> {

    public ScorchedBugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "scorched_bug")));
    }
}
