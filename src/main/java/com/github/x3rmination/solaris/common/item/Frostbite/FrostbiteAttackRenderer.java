package com.github.x3rmination.solaris.common.item.Frostbite;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaAttackEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class FrostbiteAttackRenderer extends EntityRenderer<FrostbiteAttackEntity> {

    public FrostbiteAttackRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }


    @Override
    public ResourceLocation getTextureLocation(FrostbiteAttackEntity pEntity) {
        return null;
    }
}
