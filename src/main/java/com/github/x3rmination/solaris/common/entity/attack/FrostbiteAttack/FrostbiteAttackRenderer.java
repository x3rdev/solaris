package com.github.x3rmination.solaris.common.entity.attack.FrostbiteAttack;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FrostbiteAttackRenderer extends EntityRenderer<FrostbiteAttackEntity> {

    public FrostbiteAttackRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }


    @Override
    public ResourceLocation getTextureLocation(FrostbiteAttackEntity pEntity) {
        return null;
    }
}
