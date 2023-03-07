package com.github.x3rmination.solaris;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.gameasset.Models;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class Temp {

    //for hotswap
    public static void renderTemp(LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, EntityRenderer<? extends Entity> entityRenderer, MultiBufferSource bufferSource, PoseStack poseStack, int packedLight, float partialTicks) {
        Armature armature = entityPatch.getEntityModel(Models.LOGICAL_SERVER).getArmature();
        if(entityPatch.getOriginal() instanceof Player) {
            if (entityPatch.getAnimator().getPose(partialTicks).getJointTransformData().get("Tool_R") != null) {
                OpenMatrix4f transformMatrix = Animator.getBindedJointTransformByName(entityPatch.getAnimator().getPose(partialTicks), armature, "Tool_R");
                double bodyRot = entityPatch.getOriginal().yBodyRot * Math.PI / 180F;

                double xHandPos = transformMatrix.m30 * -1;
                double zHandPos = transformMatrix.m32 * -1;

                double handX = (xHandPos * Math.cos(bodyRot)) - (zHandPos * Math.sin(bodyRot));
                double handY = transformMatrix.m31;
                double handZ = (xHandPos * Math.sin(bodyRot)) + (zHandPos * Math.cos(bodyRot));
                livingEntity.level.addParticle(ParticleTypes.FLAME, handX + livingEntity.position().x, handY + livingEntity.position().y, handZ + livingEntity.position().z, 0, 0, 0);

                Vector3f toolPos = new Vector3f(0, 0, 1);
                float[] vector = {toolPos.x(), toolPos.y(), toolPos.z()};
                toolPos.setX((float) (vector[0] * Math.cos(bodyRot) - vector[2] * Math.sin(bodyRot)));
                toolPos.setZ((float) (vector[0] * Math.sin(bodyRot) + vector[2] * Math.cos(bodyRot)));

                Quaternion rotation = transformMatrix.toQuaternion();
                rotation.set(rotation.i(), -rotation.j(), rotation.k(), rotation.r());
                toolPos.transform(rotation);

                livingEntity.level.addParticle(ParticleTypes.ENCHANT, toolPos.x() + handX + livingEntity.position().x, toolPos.y() + handY + livingEntity.position().y, toolPos.z() + handZ + livingEntity.position().z, 0, 0, 0);
            }
        }
    }
}
