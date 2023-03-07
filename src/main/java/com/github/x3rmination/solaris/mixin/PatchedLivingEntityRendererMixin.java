package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.Temp;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mixin(PatchedLivingEntityRenderer.class)
public abstract class PatchedLivingEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;Lnet/minecraft/client/renderer/entity/EntityRenderer;Lnet/minecraft/client/renderer/MultiBufferSource;Lcom/mojang/blaze3d/vertex/PoseStack;IF)V", at = @At("TAIL"), remap = false)
    private void render(LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, EntityRenderer<? extends net.minecraft.world.entity.Entity> entityRenderer, MultiBufferSource bufferSource, PoseStack poseStack, int packedLight, float partialTicks, CallbackInfo ci) {
//        Armature armature = entityPatch.getEntityModel(Models.LOGICAL_SERVER).getArmature();
//        if(entityPatch.getOriginal() instanceof Player) {
//            if (entityPatch.getAnimator().getPose(partialTicks).getJointTransformData().get("Tool_R") != null) {
//                OpenMatrix4f transformMatrix = Animator.getBindedJointTransformByName(entityPatch.getAnimator().getPose(partialTicks), armature, "Tool_R");
//                double bodyRot = Mth.wrapDegrees(entityPatch.getOriginal().yBodyRot) * Math.PI / 180F;
//                double xHandPos = transformMatrix.m30 * -1;
//                double zHandPos = transformMatrix.m32 * -1;
//
//                double handX = (xHandPos * Math.cos(bodyRot)) - (zHandPos * Math.sin(bodyRot));
//                double handY = transformMatrix.m31;
//                double handZ = (xHandPos * Math.sin(bodyRot)) + (zHandPos * Math.cos(bodyRot));
//                livingEntity.level.addParticle(ParticleTypes.FLAME, handX + livingEntity.position().x, handY + livingEntity.position().y, handZ + livingEntity.position().z, 0, 0, 0);
//
//                Vector3f toolPos = new Vector3f(1, 0, 0);
////                toolPos.setX((float) ((toolPos.x() * Math.cos(bodyRot)) - (toolPos.z() * Math.sin(bodyRot))));
////                toolPos.setZ((float) ((toolPos.x() * Math.sin(bodyRot)) + (toolPos.z() * Math.cos(bodyRot))));
//                Quaternion rotation = transformMatrix.toQuaternion();
//                rotation.set(rotation.i(), -rotation.j(), rotation.k(), rotation.r());
//                toolPos.transform(rotation);
//                livingEntity.level.addParticle(ParticleTypes.ENCHANT, toolPos.x() + handX + livingEntity.position().x, toolPos.y() + handY + livingEntity.position().y, toolPos.z() + handZ + livingEntity.position().z, 0, 0, 0);
//            }
//        }
        Temp.renderTemp(livingEntity, entityPatch, entityRenderer, bufferSource, poseStack, packedLight, partialTicks);
    }


}
