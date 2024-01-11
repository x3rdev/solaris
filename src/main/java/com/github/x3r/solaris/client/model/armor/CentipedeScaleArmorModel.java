package com.github.x3r.solaris.client.model.armor;

import com.github.x3r.solaris.Solaris;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class CentipedeScaleArmorModel extends SolarisArmorModel {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Solaris.MOD_ID, "centipede_scale_armor"), "main");
    public CentipedeScaleArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorHead = head.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(50, 17).addBox(-5.0F, -12.25F, -4.25F, 10.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = armorHead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 58).addBox(-2.75F, 0.75F, 2.25F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.175F))
                .texOffs(6, 69).addBox(-2.75F, 0.75F, 1.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, -9.25F, -6.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r2 = armorHead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 39).addBox(-0.55F, -5.0F, -3.975F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(0, 32).addBox(-0.55F, -3.05F, -3.475F, 1.0F, 3.0F, 9.0F, new CubeDeformation(-0.025F))
                .texOffs(58, 0).addBox(-0.55F, -5.0F, -2.975F, 1.0F, 2.0F, 8.0F, new CubeDeformation(-0.025F)), PartPose.offsetAndRotation(5.5F, 0.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r3 = armorHead.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 62).addBox(-1.45F, -5.0F, -3.975F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(15, 27).addBox(-0.45F, -3.05F, -3.475F, 1.0F, 3.0F, 9.0F, new CubeDeformation(-0.025F))
                .texOffs(48, 39).addBox(-0.45F, -5.0F, -2.975F, 1.0F, 2.0F, 8.0F, new CubeDeformation(-0.025F)), PartPose.offsetAndRotation(-5.5F, 0.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r4 = armorHead.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(48, 7).addBox(3.1029F, -6.3882F, -3.9828F, 1.0F, 2.0F, 8.0F, new CubeDeformation(-0.025F)), PartPose.offsetAndRotation(-0.0029F, -1.4118F, -9.2422F, -1.5708F, -1.1781F, 1.5708F));

        PartDefinition cube_r5 = armorHead.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 30).addBox(3.625F, 1.275F, 0.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(48, 49).addBox(3.625F, -0.725F, 0.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4971F, -2.2906F, 0.0F, 1.5708F, -1.1781F, -1.5708F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorBody = body.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, 1.0F, -2.5F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.025F))
                .texOffs(9, 67).addBox(-4.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.025F))
                .texOffs(32, 7).addBox(-3.5F, 7.0F, -2.5F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 62).addBox(2.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.025F))
                .texOffs(24, 0).addBox(-4.5F, 10.0F, -2.475F, 9.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone11 = armorBody.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offset(5.4796F, 14.3588F, 0.0F));

        PartDefinition cube_r6 = bone11.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(9, 60).addBox(0.8705F, -1.0F, 0.6295F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, -0.3927F));

        PartDefinition cube_r7 = bone11.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(51, 9).addBox(-1.4F, 2.625F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(51, 7).addBox(-1.4F, 2.625F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(19, 32).addBox(-1.0F, 0.9F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(32, 9).addBox(-1.0F, 0.9F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(73, 49).addBox(-1.075F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(-0.1296F, -3.7088F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r8 = bone11.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(53, 71).addBox(-0.2432F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r9 = bone11.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(20, 58).addBox(0.8705F, -1.0F, -2.6295F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, -0.3927F));

        PartDefinition cube_r10 = bone11.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(55, 59).addBox(0.9455F, -1.0F, 0.7295F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.125F)), PartPose.offsetAndRotation(-0.425F, -1.575F, 0.0F, 0.0F, -0.7854F, -0.3927F));

        PartDefinition cube_r11 = bone11.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(71, 57).addBox(-0.2182F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.425F, -1.575F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r12 = bone11.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(39, 58).addBox(0.9955F, -1.0F, -2.7045F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.125F)), PartPose.offsetAndRotation(-0.475F, -1.55F, 0.0F, 0.0F, 0.7854F, -0.3927F));

        PartDefinition cube_r13 = bone11.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(76, 23).addBox(0.375F, -1.0F, 1.45F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1296F, -3.7088F, 0.0F, 0.0F, -0.7854F, -0.3927F));

        PartDefinition cube_r14 = bone11.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(41, 77).addBox(0.4F, -1.0F, -3.475F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1296F, -3.7088F, 0.0F, 0.0F, 0.7854F, -0.3927F));

        PartDefinition bone8 = armorBody.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(-5.35F, 10.65F, 0.0F));

        PartDefinition cube_r15 = bone8.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 76).addBox(-1.375F, -1.0F, -3.45F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.3927F));

        PartDefinition cube_r16 = bone8.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(73, 6).addBox(0.05F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r17 = bone8.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(75, 63).addBox(-1.375F, -1.0F, 1.45F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.3927F));

        PartDefinition bone9 = armorBody.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(-5.0546F, 12.7838F, 0.0F));

        PartDefinition cube_r18 = bone9.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(29, 68).addBox(-1.9205F, -1.0F, -2.7045F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.125F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.3927F));

        PartDefinition cube_r19 = bone9.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(63, 71).addBox(-0.8068F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r20 = bone9.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(19, 34).addBox(0.0F, 0.9F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 44).addBox(0.0F, 0.9F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.2954F, -2.1338F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r21 = bone9.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(18, 67).addBox(-1.9455F, -1.0F, 0.7295F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.125F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.3927F));

        PartDefinition bone10 = armorBody.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offset(-5.4796F, 14.3588F, 0.0F));

        PartDefinition cube_r22 = bone10.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(11, 37).addBox(-1.8705F, -1.0F, -2.6045F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.3927F));

        PartDefinition cube_r23 = bone10.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(43, 58).addBox(0.4F, 2.625F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(59, 59).addBox(0.4F, 2.625F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.1296F, -3.7088F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r24 = bone10.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 70).addBox(-0.8068F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r25 = bone10.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(60, 4).addBox(-1.8705F, -1.0F, 0.6045F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.3927F));

        PartDefinition bone12 = armorBody.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(48, 55).addBox(0.0404F, 1.6978F, -5.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(43, 71).addBox(0.1F, -12.225F, -4.85F, 4.0F, 5.0F, 1.0F, new CubeDeformation(-1.125F))
                .texOffs(46, 39).addBox(-2.1F, -12.225F, -4.85F, 4.0F, 5.0F, 1.0F, new CubeDeformation(-1.125F))
                .texOffs(58, 45).addBox(0.0154F, -0.5272F, -5.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(24, 58).addBox(0.0404F, 0.4728F, -5.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(40, 27).addBox(0.0404F, 1.6978F, -0.475F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(48, 45).addBox(0.0404F, 0.4728F, -0.475F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(12, 44).addBox(0.0154F, -0.5272F, -0.475F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.0F, 12.325F, 2.25F));

        PartDefinition cube_r26 = bone12.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(45, 18).addBox(-0.3F, 1.55F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(12, 46).addBox(-0.3F, 1.55F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r27 = bone12.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 16).addBox(-0.15F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(24, 2).addBox(-0.15F, 1.5F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, -1.225F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r28 = bone12.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(24, 0).addBox(-0.1F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(25, 16).addBox(-0.1F, 1.5F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -2.225F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r29 = bone12.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(45, 16).addBox(-0.625F, 1.6F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(0, 46).addBox(-0.625F, 1.6F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r30 = bone12.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(5, 32).addBox(-0.775F, 1.55F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
                .texOffs(32, 7).addBox(-0.775F, 1.55F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(2.0F, -1.225F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r31 = bone12.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 18).addBox(-0.875F, 1.525F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(31, 30).addBox(-0.875F, 1.525F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(2.0F, -2.225F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r32 = bone12.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(69, 72).addBox(-1.35F, -1.35F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.325F, -6.25F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r33 = bone12.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(37, 33).addBox(1.0F, -2.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.725F, -4.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r34 = bone12.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(37, 36).addBox(-4.0F, -2.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.725F, -4.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r35 = bone12.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(28, 42).addBox(1.0F, -2.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.9F, -4.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r36 = bone12.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(48, 49).addBox(-4.0F, -2.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.9F, -4.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r37 = bone12.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(48, 52).addBox(1.0F, -2.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.075F, -4.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r38 = bone12.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(52, 4).addBox(-4.0F, -2.5F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.075F, -4.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r39 = bone12.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(11, 32).addBox(-4.1F, -2.45F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.85F, -8.425F, -4.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r40 = bone12.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 36).addBox(1.1F, -2.45F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.15F, -8.425F, -4.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(46, 23).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition armorRightArm = right_arm.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(73, 72).addBox(-3.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.75F)), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition bone = armorRightArm.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.4889F, -1.2769F, -14.0F));

        PartDefinition cube_r41 = bone.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 62).addBox(-4.5F, 2.0F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(62, 0).addBox(-4.5F, 4.0F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 64).addBox(-4.5F, 4.0F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 60).addBox(-4.5F, 2.0F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(26, 60).addBox(-4.5F, 0.0F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(4, 60).addBox(-4.5F, 0.0F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r42 = bone.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(66, 14).addBox(-1.0908F, -2.3188F, -0.1937F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8544F, 1.4877F, 14.0F, -0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r43 = bone.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(58, 23).addBox(-1.0908F, -1.764F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(-4.8544F, 1.4877F, 14.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r44 = bone.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(69, 69).addBox(-1.0908F, -2.3188F, -1.8062F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8544F, 1.4877F, 14.0F, 0.7854F, 0.0F, -0.7854F));

        PartDefinition bone2 = armorRightArm.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.4889F, -1.2769F, -14.0F));

        PartDefinition cube_r45 = bone2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(63, 71).addBox(-3.5F, 6.35F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(71, 59).addBox(-3.5F, 6.35F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r46 = bone2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(62, 31).addBox(-3.495F, -1.6188F, 0.5063F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8544F, 1.4877F, 14.0F, -0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r47 = bone2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(58, 10).addBox(-3.495F, -0.774F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(-4.8544F, 1.4877F, 14.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r48 = bone2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(55, 68).addBox(-3.495F, -1.6188F, -2.5062F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8544F, 1.4877F, 14.0F, 0.7854F, 0.0F, -0.7854F));

        PartDefinition bone3 = armorRightArm.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-4.3656F, 0.2107F, 0.0F));

        PartDefinition cube_r49 = bone3.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(26, 27).addBox(-4.9092F, -0.9188F, 1.2063F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, -0.7854F));

        PartDefinition cube_r50 = bone3.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(64, 45).addBox(-2.5F, 8.1F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(66, 57).addBox(-2.5F, 8.1F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(4.8544F, -1.4877F, -14.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r51 = bone3.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(47, 0).addBox(-4.9092F, 0.2159F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r52 = bone3.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(39, 68).addBox(-4.9092F, -0.9188F, -3.2062F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, -0.7854F));

        PartDefinition bone4 = armorRightArm.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(20, 16).addBox(-7.5F, -17.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.9F))
                .texOffs(0, 0).addBox(-7.5F, -15.45F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.75F)), PartPose.offset(4.0F, 21.5F, 0.0F));

        PartDefinition cube_r53 = bone4.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(74, 41).addBox(3.6F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.15F))
                .texOffs(74, 37).addBox(3.5F, -3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-12.0F, -13.5F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 44).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition armorLeftArm = left_arm.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(7, 74).addBox(9.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.75F))
                .texOffs(26, 30).addBox(10.5F, 6.05F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.75F))
                .texOffs(0, 32).addBox(10.5F, 4.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.9F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

        PartDefinition cube_r54 = armorLeftArm.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(17, 74).addBox(-4.6F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.15F))
                .texOffs(59, 71).addBox(-4.5F, -3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(16.0F, 8.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition bone5 = armorLeftArm.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(7.5111F, -1.2769F, -14.0F));

        PartDefinition cube_r55 = bone5.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(70, 37).addBox(3.5F, 1.65F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 69).addBox(3.5F, 3.65F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 71).addBox(3.5F, -0.35F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(53, 71).addBox(3.5F, -0.35F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(70, 57).addBox(3.5F, 1.65F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(23, 70).addBox(3.5F, 3.65F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r56 = bone5.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(70, 34).addBox(-4.9092F, -2.3188F, -1.8062F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6044F, 1.2377F, 14.0F, 0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r57 = bone5.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(62, 27).addBox(-4.9092F, -1.764F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(4.6044F, 1.2377F, 14.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r58 = bone5.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(68, 3).addBox(-4.9092F, -2.3188F, -0.1937F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6044F, 1.2377F, 14.0F, -0.7854F, 0.0F, 0.7854F));

        PartDefinition bone6 = armorLeftArm.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(7.5111F, -1.2769F, -14.0F));

        PartDefinition cube_r59 = bone6.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(33, 68).addBox(2.5F, 6.0F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(53, 68).addBox(2.5F, 6.0F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r60 = bone6.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(70, 20).addBox(-2.505F, -1.6188F, -2.5062F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6044F, 1.2377F, 14.0F, 0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r61 = bone6.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(58, 53).addBox(-2.505F, -0.774F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(4.6044F, 1.2377F, 14.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r62 = bone6.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(68, 0).addBox(-2.505F, -1.6188F, 0.5063F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6044F, 1.2377F, 14.0F, -0.7854F, 0.0F, 0.7854F));

        PartDefinition bone7 = armorLeftArm.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(12.1156F, -0.0393F, 0.0F));

        PartDefinition cube_r63 = bone7.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(70, 17).addBox(-1.0908F, -0.9188F, -3.2062F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.7854F));

        PartDefinition cube_r64 = bone7.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(68, 6).addBox(1.5F, 7.75F, 12.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(22, 67).addBox(1.5F, 7.75F, 14.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-4.6044F, -1.2377F, -14.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r65 = bone7.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(58, 49).addBox(-1.0908F, 0.2159F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r66 = bone7.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(66, 45).addBox(-1.0908F, -0.9188F, 1.2063F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.7854F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 42).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 35).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition cube_r67 = right_leg.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(23, 68).addBox(-2.3F, -2.35F, -2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.425F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(59, 59).addBox(-2.0F, 6.975F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition cube_r68 = right_boot.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(37, 30).addBox(-3.5F, -3.25F, -1.775F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(2.0F, 9.475F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 42).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(27, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition cube_r69 = left_leg.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(33, 68).addBox(1.35F, -2.35F, -2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.425F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(43, 59).addBox(-2.0F, 6.975F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition cube_r70 = left_boot.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(0, 5).addBox(0.5F, -3.25F, -1.775F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-2.0F, 9.475F, 0.0F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
