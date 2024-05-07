package com.github.x3r.solaris.client.renderer.entity;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.UrborosEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.cache.texture.AnimatableTexture;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;
import software.bernie.geckolib.util.RenderUtils;

public class UrborosRenderer extends DynamicGeoEntityRenderer<UrborosEntity> {

    protected static final ResourceLocation EYELID_TEXTURE = new ResourceLocation(Solaris.MOD_ID, "textures/entity/urboros_eyelid.png");
    public UrborosRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "urboros")));
    }

    @Nullable
    @Override
    protected ResourceLocation getTextureOverrideForBone(GeoBone bone, UrborosEntity animatable, float partialTick) {
        if(bone.getName().equals("eyelid")) {
            AnimatableTexture.setAndUpdate(EYELID_TEXTURE);
            return EYELID_TEXTURE;
        }
        return super.getTextureOverrideForBone(bone, animatable, partialTick);
    }

    @Override
    protected IntIntPair computeTextureSize(ResourceLocation texture) {
        IntIntPair pair = super.computeTextureSize(texture);
        if(pair.leftInt()/pair.rightInt() != 1) {
            return IntIntPair.of(pair.leftInt(), pair.leftInt());
        }
        return pair;
    }
}
