package com.github.x3r.solaris.client.renderer.entity;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.ElementalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ElementalRenderer extends GeoEntityRenderer<ElementalEntity> {

    public ElementalRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "elemental")) {
            @Override
            public void setCustomAnimations(ElementalEntity animatable, long instanceId, AnimationState<ElementalEntity> animationState) {
                CoreGeoBone small_cube1 = getAnimationProcessor().getBone("small_cube1");
                CoreGeoBone small_cube2 = getAnimationProcessor().getBone("small_cube2");

                if(small_cube1 != null) {
                    small_cube1.setRotY((float) (Math.toRadians(5 * animationState.animationTick)));
                    small_cube1.setPosY((float) (4*Math.sin(animationState.animationTick/20)));
                }
                if(small_cube2 != null) {
                    small_cube2.setRotY((float) Math.toRadians(5 * animationState.animationTick));
                    small_cube2.setPosY((float) (5*Math.sin(animationState.animationTick/20+90)));
                }
            }
        });
    }

    @Override
    public void actuallyRender(PoseStack poseStack, ElementalEntity animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
