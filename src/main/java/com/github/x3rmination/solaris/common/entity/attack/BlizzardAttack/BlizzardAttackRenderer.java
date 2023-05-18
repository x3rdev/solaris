package com.github.x3rmination.solaris.common.entity.attack.BlizzardAttack;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BlizzardAttackRenderer extends EntityRenderer<BlizzardAttackEntity> {

    public BlizzardAttackRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }


    @Override
    public ResourceLocation getTextureLocation(BlizzardAttackEntity pEntity) {
        return null;
    }
}
