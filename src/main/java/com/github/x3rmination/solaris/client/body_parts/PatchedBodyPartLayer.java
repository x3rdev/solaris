package com.github.x3rmination.solaris.client.body_parts;

import com.github.x3rmination.solaris.client.layer.BodyPartLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.layer.PatchedLayer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class PatchedBodyPartLayer<E extends LivingEntity, T extends LivingEntityPatch<E>, M extends HumanoidModel<E>> extends PatchedLayer<E, T, M, BodyPartLayer<E, M, M>> {

    @Override
    public void renderLayer(T entitypatch, E livingentity, BodyPartLayer<E, M, M> originalRenderer, PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, OpenMatrix4f[] poses, float netYawHead, float pitchHead, float partialTicks) {
//        ModelPart model = originalRenderer.getParentModel().getHead();
//        E entity = entitypatch.getOriginal();
//        OpenMatrix4f modelMatrix = new OpenMatrix4f();
//        modelMatrix.scale(new Vec3f(-1.0F, -1.0F, 1.0F)).mulFront(entitypatch.getEntityModel(ClientModels.LOGICAL_CLIENT).getArmature().searchJointById(9).getAnimatedTransform());
//        model.x = 0;
//        model.y = 0;
//        model.z = 0;
//        model.xRot = 0;
//        model.yRot = 0;
//        model.zRot = 0;
//        OpenMatrix4f transpose = OpenMatrix4f.transpose(modelMatrix, null);
//        matrixStackIn.pushPose();
//
//        MathUtils.translateStack(matrixStackIn, modelMatrix);
//        MathUtils.rotateStack(matrixStackIn, transpose);
//
//        if (entitypatch.getOriginal().isBaby()) {
//            matrixStackIn.translate(0.0F, -1.2F, 0.0F);
//            matrixStackIn.scale(1.6F, 1.6F, 1.6F);
//        }
//
//        originalRenderer.render(matrixStackIn, buffer, packedLightIn, entity, entity.animationPosition, entity.animationSpeed, packedLightIn, entity.tickCount, netYawHead, pitchHead);
//        matrixStackIn.popPose();
    }
}
