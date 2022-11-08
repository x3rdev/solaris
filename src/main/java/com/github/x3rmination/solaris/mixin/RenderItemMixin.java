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

        /*
        Joint positions are available in the Epic Fight Model
         */

        // Root
        JointTransform root = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Root");
        if(root == null) return;
        Vector3f rootStart = new Vector3f(0, 0.75F, 0);
        Vector3f rootEnd = new Vector3f(0, 0.05F, 0);
        rootEnd.transform(root.rotation());
        rootEnd.add(root.translation().x, root.translation().y, root.translation().z);
        rootEnd.mul(root.scale().x, root.scale().y, root.scale().z);
        rootEnd.mul(1, 1, -1);
        rootEnd.add(rootStart);
        renderVector(rootStart, rootEnd, matrix3f, matrix4f);

        // Torso
        JointTransform torso = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Torso");
        if(torso == null) return;
        Vector3f torsoStart = rootEnd.copy();
        Vector3f torsoEnd = new Vector3f(0, 0.3F, 0);
        torsoEnd.transform(torso.rotation());
        torsoEnd.add(torso.translation().x, torso.translation().y, torso.translation().z);
        torsoEnd.mul(torso.scale().x, torso.scale().y, torso.scale().z);
        torsoEnd.mul(1, 1, -1);
        torsoEnd.add(torsoStart);
        renderVector(torsoStart, torsoEnd, matrix3f, matrix4f);

        // Chest
        JointTransform chest = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Chest");
        if(chest == null) return;
        Vector3f chestStart = torsoEnd.copy();
        Vector3f chestEnd = new Vector3f(0, 0.4F, 0);
        chestEnd.transform(chest.rotation());
        chestEnd.add(chest.translation().x, chest.translation().y, chest.translation().z);
        chestEnd.mul(chest.scale().x, chest.scale().y, chest.scale().z);
        chestEnd.mul(1, 1, -1);
        chestEnd.add(chestStart);
        renderVector(chestStart, chestEnd, matrix3f, matrix4f);

        // Chest to Shoulder_R
        JointTransform shoulder = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Shoulder_R");
        if(shoulder == null) return;
        Vector3f shoulderStart = chestEnd.copy();
        Vector3f shoulderEnd = new Vector3f(0.325F, -0.125F, 0);
        shoulderEnd.transform(root.rotation());
        shoulderEnd.transform(torso.rotation());
        shoulderEnd.transform(chest.rotation());
        shoulderEnd.transform(shoulder.rotation());
        shoulderEnd.add(shoulderStart);
        renderVector(shoulderStart, shoulderEnd, matrix3f, matrix4f);

        // Shoulder_R to Arm_R
        JointTransform arm = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Arm_R");
        JointTransform elbow = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Elbow_R");
        if(arm == null) return;
        if(elbow == null) return;
        Vector3f armStart = shoulderEnd.copy();
        Vector3f armEnd = new Vector3f(0, -0.5F, 0);
        armEnd.transform(root.rotation());
        armEnd.transform(torso.rotation());
        armEnd.transform(chest.rotation());
//        armEnd.transform(shoulder.rotation());
        armEnd.mul(1, 1, -1);
        armEnd.transform(arm.rotation());
        armEnd.transform(elbow.rotation());
        armEnd.add(arm.translation().x, arm.translation().y, arm.translation().z);
        armEnd.mul(arm.scale().x, arm.scale().y, arm.scale().z);
        armEnd.add(armStart);
        renderVector(armStart, armEnd, matrix3f, matrix4f);

        // Arm_R to Hand_R
        JointTransform handBone = entitypatch.getAnimator().getPose(4.0F).getJointTransformData().get("Hand_R");
        if(handBone == null) return;
        Vector3f handStart = armEnd.copy();
        Vector3f handEnd = new Vector3f(0, -0.325F, 0);
        handEnd.transform(root.rotation());
        handEnd.transform(torso.rotation());
        handEnd.transform(chest.rotation());
        handEnd.transform(shoulder.rotation());
        handEnd.transform(arm.rotation());
        handEnd.transform(handBone.rotation());
        handEnd.transform(elbow.rotation());
        handEnd.mul(1, 1, -1);
        handEnd.add(handStart);
        renderVector(handStart, handEnd, matrix3f, matrix4f);
    }

    private void renderVector(Vector3f startVertex, Vector3f endVertex, Matrix3f matrix3f, Matrix4f matrix4f) {
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