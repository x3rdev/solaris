package com.github.x3rmination.solaris.common.entity.attack.CloudSplitterAttack;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CloudSplitterAttackRenderer extends EntityRenderer<CloudSplitterAttackEntity> {

    public CloudSplitterAttackRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }


    @Override
    public ResourceLocation getTextureLocation(CloudSplitterAttackEntity pEntity) {
        return null;
    }
}
