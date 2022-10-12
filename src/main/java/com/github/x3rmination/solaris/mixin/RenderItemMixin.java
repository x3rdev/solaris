package com.github.x3rmination.solaris.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.JointTransform;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.gameasset.Models;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.Map;
import java.util.Random;

@Mixin(RenderItemBase.class)
public abstract class RenderItemMixin {

    @Inject(method = "renderItemInHand", at = @At("TAIL"), remap = false)
    public void renderItemInHandMixin(ItemStack stack, LivingEntityPatch<?> entitypatch, InteractionHand hand, MultiBufferSource buffer, PoseStack poseStack, int packedLight, CallbackInfo ci) {
        Armature armature = entitypatch.getEntityModel(Models.LOGICAL_SERVER).getArmature();
        armature.initializeTransform();
        Matrix3f matrix3f = poseStack.last().normal();
        Matrix4f matrix4f = poseStack.last().pose();

//        // Elbow
//        double torsoX = 0.0;
//        double torsoY = 1.5;
//        double torsoZ = 0.0;
//        JointTransform Root = entitypatch.getAnimator().getPose(0.0F).getJointTransformData().get("Root");
////        System.out.println(entitypatch.getAnimator().getPose(0.0F).getJointTransformData());
//        if(Root == null) return;
//        Vec3f RootTranslation = Root.translation();
//        Vector3f RootStart = new Vector3f((float) (torsoX + RootTranslation.x), (float) (torsoY + RootTranslation.y), (float) (torsoZ + RootTranslation.z));
//        Vector3f RootEnd = new Vector3f((float) (torsoX + RootTranslation.x) +  0.5F, (float) (torsoY + RootTranslation.y), (float) (torsoZ + RootTranslation.z));
////        RootEnd.transform(Root.rotation());
//        renderVector(RootStart, RootEnd, matrix3f, matrix4f, buffer);

        // Navel
        JointTransform root = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Root");
        if(root == null) return;
        Vector3f navelStart = new Vector3f(0, 0, 0);
        Vector3f navelEnd = new Vector3f(0 + root.translation().x, 0.75F + root.translation().y, 0 + root.translation().z);
        navelEnd.transform(root.rotation());
        renderVector(navelStart, navelEnd, matrix3f, matrix4f, buffer);

        // Shoulder
        JointTransform torso = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Torso");
        JointTransform chest = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Chest");
        if(torso == null) return;
        if(chest == null) return;
        Vector3f shoulderStart = navelEnd.copy();
        Vector3f shoulderEnd = new Vector3f(0.25F, 1.5F + root.translation().y, 0F);
        shoulderEnd.transform(root.rotation());
        shoulderEnd.transform(chest.rotation());
        shoulderEnd.mul(1, 1, -1);
        renderVector(shoulderStart, shoulderEnd, matrix3f, matrix4f, buffer);

        // Elbow
//        System.out.println(entitypatch.getAnimator().getPose(4.0F).getJointTransformData());
        JointTransform arm = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Arm_R");
        JointTransform shoulder = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Shoulder_R");
        if(arm == null) return;
        if(shoulder == null) return;
        Vector3f elbowStart = shoulderEnd.copy();
        Vector3f elbowEnd = new Vector3f(0.325F, 1.125F + root.translation().y, 0F);
        elbowEnd.add(shoulder.translation().x, shoulder.translation().y, shoulder.translation().z);
        renderVector(elbowStart, elbowEnd, matrix3f, matrix4f, buffer);

        //Hand

    }

    private void renderVector(Vector3f startVertex, Vector3f endVertex, Matrix3f matrix3f, Matrix4f matrix4f, MultiBufferSource buffer) {
        Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.LINES).vertex(matrix4f, startVertex.x(), startVertex.y(), startVertex.z()).color(0, 0, 255, 255).normal(matrix3f, startVertex.x(), startVertex.y(), startVertex.z()).endVertex();
        Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.LINES).vertex(matrix4f, endVertex.x(), endVertex.y(), endVertex.z()).color(0, 255, 0, 255).normal(matrix3f, endVertex.x(), endVertex.y(), endVertex.z()).endVertex();
    }

    private void swingEffect(JointTransform jointTransform, LocalPlayer player) {
        ItemStack itemStack = player.getMainHandItem();
        Random random = new Random();
        ClientLevel level = Minecraft.getInstance().level;
        if(level == null) {
            return;
        }
        double x = player.position().x;
        double y = player.position().y;
        double z = player.position().z;
//        System.out.println(toolVec);
//        if(itemStack.getItem() == ItemRegistry.FLAMING_FLAMBERGE.get()) {
//            level.addParticle(ParticleTypes.FLAME, x + toolVec.x, y + toolVec.y, z + toolVec.z, random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F));
//        }
//        if(itemStack.getItem() == ItemRegistry.FIRE_KATANA.get()) {
//            level.addParticle(ParticleTypes.FLAME, x + toolVec.x, y + toolVec.y, z + toolVec.z, 0, 0, 0);
//        }
//        if(itemStack.getItem() == ItemRegistry.FROSTBITE.get()) {
//            level.addParticle(ParticleTypes.SNOWFLAKE, x + toolVec.x, y + toolVec.y, z + toolVec.z, random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F));
//        }
//        if(itemStack.getItem() == ItemRegistry.ICE_HALBERD.get()) {
//            level.addParticle(ParticleTypes.SNOWFLAKE, x + toolVec.x, y + toolVec.y, z + toolVec.z, random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F));
//        }
    }
}
