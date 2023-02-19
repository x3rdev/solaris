package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
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

@Mixin(PatchedLivingEntityRenderer.class)
public abstract class PatchedLivingEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;Lnet/minecraft/client/renderer/entity/EntityRenderer;Lnet/minecraft/client/renderer/MultiBufferSource;Lcom/mojang/blaze3d/vertex/PoseStack;IF)V", at = @At("TAIL"), remap = false)
    private void render(LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, EntityRenderer<? extends net.minecraft.world.entity.Entity> entityRenderer, MultiBufferSource bufferSource, PoseStack poseStack, int packedLight, float partialTicks, CallbackInfo ci) {
        Armature armature = entityPatch.getEntityModel(Models.LOGICAL_SERVER).getArmature();
        if(entityPatch.getOriginal() instanceof Player) {
            if (entityPatch.getAnimator().getPose(partialTicks).getJointTransformData().get("Tool_R") != null) {
                OpenMatrix4f transformMatrix = Animator.getBindedJointTransformByName(entityPatch.getAnimator().getPose(partialTicks), armature, "Tool_R");
                double bodyRot = Mth.wrapDegrees(entityPatch.getOriginal().yBodyRot) * Math.PI / 180F;
                double xHandPos = transformMatrix.m30 * -1;
                double zHandPos = transformMatrix.m32 * -1;

                double handX = (xHandPos * Math.cos(bodyRot)) - (zHandPos * Math.sin(bodyRot)) + livingEntity.position().x;
                double handZ = (xHandPos * Math.sin(bodyRot)) + (zHandPos * Math.cos(bodyRot)) + livingEntity.position().z;
                livingEntity.level.addParticle(ParticleTypes.FLAME, handX, livingEntity.position().y + transformMatrix.m31, handZ, 0, 0, 0);

//                double toolX = ((xHandPos + 1) * Math.cos(bodyRot)) - ((zHandPos + 1) * Math.sin(bodyRot));
//                double toolY = transformMatrix.m31;
//                double toolZ = ((xHandPos + 1) * Math.sin(bodyRot)) + ((zHandPos + 1) * Math.cos(bodyRot));
                Vector3f toolPos = new Vector3f(0, 0, 1);
                Quaternion rotation = transformMatrix.toQuaternion();
                rotation.set(-rotation.i(), rotation.j(), rotation.k(), -rotation.r());
                toolPos.transform(rotation);
                livingEntity.level.addParticle(ParticleTypes.FLAME, toolPos.x() + livingEntity.position().x, toolPos.y() + livingEntity.position().y, toolPos.z() + livingEntity.position().z, 0, 0, 0);

                Vector3f toolPostest = new Vector3f(0, 0, 1);
                Quaternion rotationtest = transformMatrix.toQuaternion();
                toolPostest.transform(rotationtest);
                livingEntity.level.addParticle(ParticleTypes.CRIT, toolPostest.x() + livingEntity.position().x, toolPostest.y() + livingEntity.position().y, toolPostest.z() + livingEntity.position().z, 0, 0, 0);
            }
        }
        entityPatch.getOriginal().getOffhandItem();
    }


}
