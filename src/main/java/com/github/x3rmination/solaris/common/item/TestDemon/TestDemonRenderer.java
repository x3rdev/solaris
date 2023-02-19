package com.github.x3rmination.solaris.common.item.TestDemon;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class TestDemonRenderer extends GeoItemRenderer<TestDemonItem> {
    public TestDemonRenderer() {
        super(new TestDemonModel());
    }

    @Override
    public void render(TestDemonItem animatable, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ItemStack stack) {
        super.render(animatable, poseStack, bufferSource, packedLight, stack);

        poseStack.pushPose();
        poseStack.scale(1.02F, 1.02F, 1.02F);
        poseStack.translate(0.49f, 0.49f, 0.49f);
        GeoModel model = this.modelProvider.getModel(this.modelProvider.getModelLocation(animatable));
        RenderSystem.setShaderTexture(0, getTextureLocation(animatable));
//        RenderType renderType = RenderType.eyes(new ResourceLocation("textures/block/fire_0.png"));
        RenderType renderType = RenderType.glintTranslucent();
        this.render(model, animatable, 0, renderType, poseStack, bufferSource, null, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1,
                50);
        poseStack.popPose();
    }
}
