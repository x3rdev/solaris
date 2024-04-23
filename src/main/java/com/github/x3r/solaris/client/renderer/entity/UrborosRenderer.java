package com.github.x3r.solaris.client.renderer.entity;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.UrborosEntity;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.cache.texture.AnimatableTexture;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class UrborosRenderer extends DynamicGeoEntityRenderer<UrborosEntity> {

    protected static final ResourceLocation EYELID_TEXTURE = new ResourceLocation(Solaris.MOD_ID, "textures/entity/urboros_eyelid.png");
    public UrborosRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(Solaris.MOD_ID, "urboros")));
    }

    @Nullable
    @Override
    protected ResourceLocation getTextureOverrideForBone(GeoBone bone, UrborosEntity animatable, float partialTick) {
        if(bone.getName().equals("eyelid")) {
            AnimatableTexture.setAndUpdate(EYELID_TEXTURE, animatable.getId() + (int)animatable.getTick(animatable));
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
