package com.github.x3rmination.solaris.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
                double degrees = Mth.wrapDegrees(entityPatch.getOriginal().yBodyRot);
                double radians = degrees * Math.PI / 180F;
                double xHandPos = transformMatrix.m30 * -1;
                double zHandPos = transformMatrix.m32 * -1;
                double xMod = (xHandPos * Math.cos(radians)) - (zHandPos * Math.sin(radians));
                double zMod = (xHandPos * Math.sin(radians)) + (zHandPos * Math.cos(radians));
//                livingEntity.level.addParticle(ParticleTypes.WAX_OFF, livingEntity.position().x + xMod, livingEntity.position().y + transformMatrix.m31, livingEntity.position().z + zMod, 0, 0, 0);
            }
        }
        entityPatch.getOriginal().getOffhandItem();
    }


}
