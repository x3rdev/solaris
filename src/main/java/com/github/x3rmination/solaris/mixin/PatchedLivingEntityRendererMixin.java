package com.github.x3rmination.solaris.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.particles.ParticleTypes;
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
        if(entityPatch.getOriginal() instanceof Player player) {
            if (entityPatch.getAnimator().getPose(partialTicks).getJointTransformData().get("Tool_R") != null) {
                OpenMatrix4f transformMatrix = Animator.getBindedJointTransformByName(entityPatch.getAnimator().getPose(partialTicks), armature, "Tool_R");
                double degrees = entityPatch.getOriginal().getYRot();
                System.out.println(degrees);
                double radians = degrees * Math.PI / 180;
                livingEntity.level.addParticle(ParticleTypes.ASH, livingEntity.getX() + (Math.sin(radians - 90) * transformMatrix.m30), livingEntity.getY() + transformMatrix.m31, livingEntity.getZ() + (Math.cos(radians - 90) * transformMatrix.m32), 0, 0, 0);
            }
        }
    }


}
