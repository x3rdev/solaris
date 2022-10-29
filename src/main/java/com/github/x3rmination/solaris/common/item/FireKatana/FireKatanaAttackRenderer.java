package com.github.x3rmination.solaris.common.item.FireKatana;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class FireKatanaAttackRenderer extends EntityRenderer<FireKatanaAttackEntity> {

    public FireKatanaAttackRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(FireKatanaAttackEntity pEntity) {
        return null;
    }
}
