package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.common.item.SolarisParticleWeapon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;
import yesman.epicfight.gameasset.Models;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.Arrays;
import java.util.Random;

@Mixin(PatchedLivingEntityRenderer.class)
public abstract class PatchedLivingEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;Lnet/minecraft/client/renderer/entity/EntityRenderer;Lnet/minecraft/client/renderer/MultiBufferSource;Lcom/mojang/blaze3d/vertex/PoseStack;IF)V", at = @At("TAIL"), remap = false)
    private void render(LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, EntityRenderer<? extends net.minecraft.world.entity.Entity> entityRenderer, MultiBufferSource bufferSource, PoseStack poseStack, int packedLight, float partialTicks, CallbackInfo ci) {
        if(livingEntity.getMainHandItem().getItem() instanceof SolarisParticleWeapon particleWeapon) {
            for(int i = 0; i < particleWeapon.getParticles().length; i++) {
                if((livingEntity.tickCount + i*4) % particleWeapon.getParticleDelay() == 0) {
                    renderParticle(particleWeapon.getParticles()[i], particleWeapon.getParticleType(), livingEntity, entityPatch, partialTicks);
                }
            }
        }
    }

    public void renderParticle(Vector3f inputVector, ParticleOptions particleType, LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, float partialTicks) {
        Armature armature = entityPatch.getEntityModel(Models.LOGICAL_SERVER).getArmature();
        if (entityPatch.getAnimator().getPose(partialTicks).getJointTransformData().get("Tool_R") != null) {
            OpenMatrix4f transformMatrix = Animator.getBindedJointTransformByName(entityPatch.getAnimator().getPose(partialTicks), armature, "Tool_R");
            double bodyRot = entityPatch.getOriginal().yBodyRot * Math.PI / 180F;

            double xHandPos = transformMatrix.m30 * -1;
            double zHandPos = transformMatrix.m32 * -1;

            double handX = (xHandPos * Math.cos(bodyRot)) - (zHandPos * Math.sin(bodyRot));
            double handY = transformMatrix.m31;
            double handZ = (xHandPos * Math.sin(bodyRot)) + (zHandPos * Math.cos(bodyRot));

            Vector3f toolPos = inputVector.copy();
            Quaternion rotation = transformMatrix.toQuaternion();
            rotation.set(rotation.i(), -rotation.j(), rotation.k(), rotation.r());
            toolPos.transform(rotation);
            float[] vector = {toolPos.x(), toolPos.y(), toolPos.z()};
            toolPos.setX((float) (vector[0] * Math.cos(bodyRot) - vector[2] * Math.sin(bodyRot)));
            toolPos.setZ((float) (vector[0] * Math.sin(bodyRot) + vector[2] * Math.cos(bodyRot)));


            Random random = livingEntity.getRandom();
            livingEntity.level.addParticle(particleType, toolPos.x() + handX + livingEntity.position().x + (0.2 * (random.nextDouble() - 0.5)), toolPos.y() + handY + livingEntity.position().y + (0.2 * (random.nextDouble() - 0.5)), toolPos.z() + handZ + livingEntity.position().z + (0.2 * (random.nextDouble() - 0.5)), 0, 0, 0);
        }
    }
}
