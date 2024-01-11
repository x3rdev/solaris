package com.github.x3r.solaris.mixin;

import org.spongepowered.asm.mixin.Mixin;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;

@Mixin(PatchedLivingEntityRenderer.class)
public abstract class PatchedLivingEntityRendererMixin {

//    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;Lnet/minecraft/client/renderer/entity/EntityRenderer;Lnet/minecraft/client/renderer/MultiBufferSource;Lcom/mojang/blaze3d/vertex/PoseStack;IF)V", at = @At("TAIL"), remap = false)
//    private void render(LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, EntityRenderer<? extends net.minecraft.world.entity.Entity> entityRenderer, MultiBufferSource bufferSource, PoseStack poseStack, int packedLight, float partialTicks, CallbackInfo ci) {
//        if(Minecraft.getInstance().isWindowActive() && livingEntity.getMainHandItem().getItem() instanceof SolarisParticleWeapon particleWeapon) {
//            for(int i = 0; i < particleWeapon.getParticles().length; i++) {
//                if((livingEntity.tickCount + i*4) % particleWeapon.getParticleDelay() == 0) {
//                    renderParticle(particleWeapon.getParticles()[i], particleWeapon.getParticleType(), livingEntity, entityPatch, partialTicks);
//                }
//            }
//        }
//    }
//
//    public void renderParticle(Vector3f inputVector, ParticleOptions particleType, LivingEntity livingEntity, LivingEntityPatch<? extends LivingEntity> entityPatch, float partialTicks) {
//        Armature armature = entityPatch.getEntityModel(Models.LOGICAL_SERVER).getArmature();
//        if (entityPatch.getAnimator().getPose(partialTicks).getJointTransformData().get("Tool_R") != null) {
//            OpenMatrix4f transformMatrix = Animator.getBindedJointTransformByName(entityPatch.getAnimator().getPose(partialTicks), armature, "Tool_R");
//            double bodyRot = entityPatch.getOriginal().yBodyRot * Math.PI / 180F;
//
//            double xHandPos = transformMatrix.m30 * -1;
//            double zHandPos = transformMatrix.m32 * -1;
//
//            double handX = (xHandPos * Math.cos(bodyRot)) - (zHandPos * Math.sin(bodyRot));
//            double handY = transformMatrix.m31;
//            double handZ = (xHandPos * Math.sin(bodyRot)) + (zHandPos * Math.cos(bodyRot));
//
//            Vector3f toolPos = inputVector.copy();
//            Quaternion rotation = transformMatrix.toQuaternion();
//            rotation.set(rotation.i(), -rotation.j(), rotation.k(), rotation.r());
//            toolPos.transform(rotation);
//            float[] vector = {toolPos.x(), toolPos.y(), toolPos.z()};
//            toolPos.setX((float) (vector[0] * Math.cos(bodyRot) - vector[2] * Math.sin(bodyRot)));
//            toolPos.setZ((float) (vector[0] * Math.sin(bodyRot) + vector[2] * Math.cos(bodyRot)));
//
//            Random random = livingEntity.getRandom();
//            livingEntity.level.addParticle(particleType, toolPos.x() + handX + livingEntity.position().x + (0.1 * (random.nextDouble() - 0.5)) + livingEntity.getDeltaMovement().x, toolPos.y() + handY + livingEntity.position().y + (0.1 * (random.nextDouble() - 0.5)) + livingEntity.getDeltaMovement().y, toolPos.z() + handZ + livingEntity.position().z + (0.1 * (random.nextDouble() - 0.5)) + livingEntity.getDeltaMovement().z, 0, 0, 0);
//        }
//    }
}
