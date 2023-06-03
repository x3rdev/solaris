package com.github.x3rmination.solaris.client.model.armor;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class MonkArmorModel extends SolarisArmorModel {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Solaris.MOD_ID, "monk_armor"), "main");

    public MonkArmorModel(ModelPart root) {
        super(root);
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 8).addBox(-4.0F, -6.75F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.35F))
                .texOffs(21, 34).addBox(-2.0F, -6.75F, -4.75F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 18).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(41, 34).addBox(-3.0F, 10.484F, 1.7275F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.025F))
                .texOffs(0, 33).addBox(-4.0F, 8.0F, -2.5F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 62).addBox(-4.0F, -4.85F, -0.65F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.5F, 4.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 70).addBox(-2.5F, -3.5F, -2.775F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 16.234F, 3.7275F, 0.5299F, 0.7119F, 0.3655F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(66, 21).addBox(-1.25F, -5.65F, -2.75F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 16.234F, 3.7275F, 0.1572F, 0.8481F, -0.1572F));

        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 0).addBox(-0.875F, -5.5F, -1.175F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0016F, 16.4404F, 0.3671F, 2.1545F, 1.4464F, 1.8825F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 71).addBox(-1.425F, -5.0F, -1.725F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.079F, 16.5686F, -3.2355F, 3.0044F, 0.7119F, 2.7761F));

        PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 71).addBox(-2.8F, -0.75F, -0.475F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1651F, 17.1217F, -3.3457F, -2.9387F, 0.6986F, -2.9892F));

        PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(66, 63).addBox(-0.5F, -3.5F, -2.775F, 3.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 16.234F, 3.7275F, 0.5299F, -0.7119F, -0.3655F));

        PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(58, 63).addBox(-1.75F, -5.65F, -2.75F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 16.234F, 3.7275F, 0.1572F, -0.8481F, 0.1572F));

        PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(50, 63).addBox(-2.125F, -5.5F, -1.175F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0016F, 16.4404F, 0.3671F, 2.1545F, -1.4464F, -1.8825F));

        PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(71, 32).addBox(0.8F, -0.75F, -0.475F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1651F, 17.1217F, -3.3457F, -2.9387F, -0.6986F, 2.9892F));

        PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(26, 70).addBox(-0.575F, -5.0F, -1.725F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.079F, 16.5686F, -3.2355F, 3.0044F, -0.7119F, -2.7761F));

        PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 46).addBox(-1.0F, -1.125F, -1.175F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(4.5696F, 10.4717F, -0.88F, 2.5472F, 1.4464F, 1.8825F));

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(12, 42).addBox(-1.025F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5182F, 10.7194F, -2.236F, -2.8861F, 0.7119F, 2.7761F));

        PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(39, 0).addBox(-1.525F, -0.9F, -0.925F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7729F, 10.4522F, 1.3815F, 0.5499F, 0.8481F, -0.1572F));

        PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(18, 42).addBox(-0.975F, -1.9F, -0.975F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9557F, 10.8379F, -2.6605F, -2.8861F, -0.7119F, -2.7761F));

        PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(46, 18).addBox(-1.0F, -1.125F, -1.175F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-4.5696F, 10.4717F, -0.88F, 2.5472F, -1.4464F, -1.8825F));

        PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(21, 18).addBox(-1.475F, -0.9F, -0.925F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7729F, 10.4522F, 1.3815F, 0.5499F, -0.8481F, 0.1572F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(50, 18).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-3.5F, 2.25F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.25F))
                .texOffs(32, 53).addBox(-3.5F, 4.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.35F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 34).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 53).addBox(-1.5F, 4.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.35F))
                .texOffs(0, 0).addBox(3.7266F, -0.8853F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.025F))
                .texOffs(32, 46).addBox(3.3043F, -3.769F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition cube_r18 = left_arm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(67, 53).addBox(-0.2482F, -2.1353F, -0.325F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(0.0018F, -0.3853F, 0.425F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4777F, -1.6337F, 2.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition cube_r19 = left_arm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(44, 71).addBox(-0.5982F, -2.1353F, -0.75F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 6).addBox(-0.3482F, -0.3853F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4777F, -1.6337F, -2.0F, 0.0F, 0.3927F, 0.0F));

        PartDefinition cube_r20 = left_arm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(47, 53).addBox(-0.5982F, -2.1353F, -0.75F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-0.3482F, -0.3853F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9F, 1.25F, -2.0F, 0.0F, 0.3927F, 0.0F));

        PartDefinition cube_r21 = left_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 16).addBox(0.0018F, -0.3853F, 0.425F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(55, 34).addBox(-0.2482F, -2.1353F, -0.325F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9F, 1.25F, 2.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 42).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(41, 41).addBox(-2.5F, -0.05F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(18, 62).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition cube_r22 = right_boot.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(46, 38).addBox(-2.5F, 0.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.225F)), PartPose.offsetAndRotation(2.0F, 8.7701F, -1.4F, 0.2849F, -0.274F, 0.7459F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(26, 34).addBox(-2.5F, -0.05F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(34, 63).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition cube_r23 = left_boot.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 58).addBox(0.5F, 0.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.225F)), PartPose.offsetAndRotation(-2.0F, 8.7701F, -1.4F, 0.2849F, 0.274F, -0.7459F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
