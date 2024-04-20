package com.github.x3r.solaris.client.renderer.entity;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.SnowTrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SnowTrollRenderer extends GeoEntityRenderer<SnowTrollEntity> {

    public SnowTrollRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "snow_troll")) {

            @Override
            public void setCustomAnimations(SnowTrollEntity animatable, long instanceId, AnimationState<SnowTrollEntity> animationState) {
                CoreGeoBone head = getAnimationProcessor().getBone("head");

                if (head != null && !animationState.isCurrentAnimation(SnowTrollEntity.DEATH) && !animationState.isCurrentAnimation(SnowTrollEntity.SNIFF)) {
                    EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

                    head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
                    head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
                }
            }
        });
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
