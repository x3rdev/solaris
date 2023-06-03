package com.github.x3rmination.solaris.client.model.armor;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class WhitestoneArmorModel extends SolarisArmorModel {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Solaris.MOD_ID, "whitestone_armor"), "main");

    public WhitestoneArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-0.1F, -0.4F, 0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 59).mirror().addBox(-0.5F, -1.4F, -0.75F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(49, 7).mirror().addBox(-0.5F, 1.6F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 49).mirror().addBox(-0.5F, -1.4F, 1.25F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 34).mirror().addBox(-0.5F, -1.4F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2981F, -2.4633F, -5.002F, 0.1097F, -0.8003F, -0.0179F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 0).addBox(-5.6F, 1.8F, -5.6F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(46, 42).addBox(-6.0F, 0.8F, -6.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(46, 10).addBox(-6.0F, 2.8F, -6.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.3622F, -0.7519F, -0.2533F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 34).addBox(-0.5F, -1.4F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-0.9F, -0.4F, 0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 49).addBox(-0.5F, -1.4F, 1.25F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 7).addBox(-0.5F, 1.6F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-0.5F, -1.4F, -0.75F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2981F, -2.4633F, -5.002F, 0.1097F, 0.8003F, 0.0179F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-0.5F, -2.2F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.2363F, -5.7216F, -4.1074F, 0.0224F, -0.8003F, -0.0179F));

        PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 30).addBox(-0.5F, -2.2F, -0.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2363F, -5.7216F, -4.1074F, 0.0224F, 0.8003F, 0.0179F));

        PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(56, 57).mirror().addBox(-0.75F, 1.25F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(46, 12).mirror().addBox(-0.75F, 0.25F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 34).mirror().addBox(-0.75F, -0.75F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 45).mirror().addBox(-0.925F, 0.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(45, 7).mirror().addBox(-0.75F, 0.25F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 34).addBox(-9.25F, -0.75F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 12).addBox(-9.25F, 0.25F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 57).addBox(-9.25F, 1.25F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(45, 7).addBox(-9.25F, 0.25F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-9.075F, 0.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -5.8F, -0.5F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(64, 39).mirror().addBox(-0.6F, -1.025F, -1.375F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 39).addBox(-8.7F, -1.025F, -1.375F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.15F, -6.2974F, 0.9003F, 0.3927F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-3.95F, 1.0F, -2.5F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.075F))
                .texOffs(48, 27).addBox(-3.0F, 9.975F, -2.5F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(63, 0).addBox(-3.95F, -0.15F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.075F))
                .texOffs(41, 49).addBox(2.05F, -0.15F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.075F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 30).addBox(0.5F, -2.5F, -9.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 6.95F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-5.5F, -2.5F, -9.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 14.0F, 6.95F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(28, 61).addBox(-2.2121F, -1.1933F, -1.4909F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(0.7879F, -1.1933F, -1.7409F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 49).addBox(-2.2121F, -2.1933F, -1.7409F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0041F, 2.4925F, -2.4217F, 0.7197F, 0.6603F, 0.4691F));

        PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(46, 17).mirror().addBox(-4.2F, 5.1F, 0.075F, 3.0F, 1.75F, 1.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(1.0F, 5.0F, -4.075F, 0.0F, 0.3927F, 0.0F));

        PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 30).addBox(3.2F, -4.725F, 0.075F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(54, 7).addBox(1.2F, -3.425F, 0.075F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(41, 48).addBox(4.2F, -1.15F, 0.075F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(66, 25).addBox(2.2F, -1.15F, 0.575F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(45, 0).addBox(1.2F, -1.15F, 0.075F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(59, 54).addBox(1.2F, 4.125F, 0.075F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(-1.0F, 5.0F, -4.5F, 0.0F, -0.3927F, 0.0F));

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 34).addBox(-0.7821F, -1.2934F, -1.4968F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0041F, 2.4925F, -2.4217F, 0.7197F, -0.6603F, -0.4691F));

        PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(20, 30).mirror().addBox(-5.2F, -4.725F, 0.075F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)).mirror(false)
                .texOffs(54, 7).mirror().addBox(-5.2F, -3.425F, 0.075F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.15F)).mirror(false)
                .texOffs(41, 48).mirror().addBox(-5.2F, -1.15F, 0.075F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.15F)).mirror(false)
                .texOffs(66, 25).mirror().addBox(-4.2F, -1.15F, 0.575F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.15F)).mirror(false)
                .texOffs(45, 0).mirror().addBox(-2.2F, -1.15F, 0.075F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.15F)).mirror(false)
                .texOffs(59, 54).mirror().addBox(-5.2F, 4.125F, 0.075F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(1.0F, 5.0F, -4.5F, 0.0F, 0.3927F, 0.0F));

        PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(46, 17).addBox(1.2F, 5.1F, 0.075F, 3.0F, 1.75F, 1.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(-1.0F, 5.0F, -4.075F, 0.0F, -0.3927F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 43).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(60, 57).mirror().addBox(-2.985F, -2.15F, -1.975F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.15F)).mirror(false)
                .texOffs(24, 0).mirror().addBox(-2.985F, 6.0F, -1.975F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition cube_r16 = right_arm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(64, 7).mirror().addBox(-8.5F, -28.0F, 5.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 7).mirror().addBox(-8.5F, -28.0F, 10.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 10).mirror().addBox(-8.5F, -28.0F, 6.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(13.0F, 20.75F, -8.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r17 = right_arm.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(36, 2).mirror().addBox(3.1F, -0.25F, -0.725F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 10).mirror().addBox(2.8F, -0.25F, 0.275F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 43).mirror().addBox(2.8F, -0.25F, -1.725F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 32).mirror().addBox(2.8F, 0.75F, -1.725F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 43).mirror().addBox(2.8F, -1.25F, -1.725F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.5F, 6.1F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r18 = right_arm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-0.475F, -3.5F, -3.5F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 59).mirror().addBox(-0.975F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-0.975F, -3.5F, 3.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(66, 17).mirror().addBox(-0.975F, -3.5F, -4.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 0).mirror().addBox(-0.975F, -4.5F, -4.5F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 49).mirror().addBox(-0.975F, 2.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.2002F, -3.0798F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-1.015F, 6.0F, -1.975F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(60, 57).addBox(0.985F, -2.15F, -1.975F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.15F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition cube_r19 = left_arm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(32, 10).addBox(-3.8F, -0.25F, 0.275F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 2).addBox(-4.1F, -0.25F, -0.725F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 32).addBox(-3.8F, 0.75F, -1.725F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 43).addBox(-3.8F, -0.25F, -1.725F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 43).addBox(-3.8F, -1.25F, -1.725F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, 6.1F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r20 = left_arm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(32, 48).addBox(-0.525F, -3.5F, -3.5F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-1.025F, -4.5F, -4.5F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.025F, -3.5F, 3.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 17).addBox(-1.025F, -3.5F, -4.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(48, 49).addBox(-1.025F, 2.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-1.025F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2002F, -3.0798F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r21 = left_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(64, 7).addBox(3.5F, -28.0F, 10.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 10).addBox(3.5F, -28.0F, 6.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(64, 7).addBox(3.5F, -28.0F, 5.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 20.75F, -8.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(28, 48).mirror().addBox(-2.0F, 4.5F, -2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.4F)).mirror(false)
                .texOffs(44, 57).mirror().addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition cube_r22 = right_boot.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(32, 12).mirror().addBox(8.15F, -0.85F, -8.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(20, 34).mirror().addBox(7.85F, -0.85F, -7.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(21, 16).mirror().addBox(7.85F, 0.15F, -9.7F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 0).mirror().addBox(7.85F, -0.85F, -9.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(15, 30).mirror().addBox(7.85F, -1.85F, -9.7F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.05F, 1.3856F, -10.5889F, 0.0F, -1.5708F, 0.7854F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(36, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(44, 57).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(28, 48).addBox(-2.0F, 4.5F, -2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition cube_r23 = left_boot.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(15, 30).addBox(-8.85F, -1.85F, -9.7F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-8.85F, -0.85F, -9.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 16).addBox(-8.85F, 0.15F, -9.7F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 34).addBox(-8.85F, -0.85F, -7.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 12).addBox(-9.15F, -0.85F, -8.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.05F, 1.3856F, -10.5889F, 0.0F, 1.5708F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
