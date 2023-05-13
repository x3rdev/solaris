package com.github.x3rmination.solaris.client.model.armor;

import com.github.x3rmination.solaris.Solaris;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SolarArmorModel extends SolarisArmorModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Solaris.MOD_ID, "solar_armor"), "main");

    public SolarArmorModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armor_head = head.addOrReplaceChild("armor_head", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition cube_r1 = armor_head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 15).addBox(0.25F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -1.1533F, 1.7706F, 0.3981F, 0.1611F, 0.0674F));

        PartDefinition cube_r2 = armor_head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 58).addBox(-0.25F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -1.1533F, 1.7706F, 0.3981F, -0.1611F, -0.0674F));

        PartDefinition cube_r3 = armor_head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 21).addBox(4.025F, -4.0F, -5.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(48, 9).addBox(-5.05F, -4.0F, -5.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r4 = armor_head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 0).addBox(-5.0F, -1.5F, -1.0F, 10.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armor_body = body.addOrReplaceChild("armor_body", CubeListBuilder.create().texOffs(26, 10).addBox(-4.0F, -4.5F, -3.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(68, 51).addBox(2.55F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(72, 62).addBox(-3.0F, 0.5F, -3.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 43).addBox(-4.5F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(72, 59).addBox(-3.0F, 0.5F, 2.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition cube_r5 = armor_body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(85, 60).addBox(-5.5F, -2.0F, -9.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, 7.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r6 = armor_body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(85, 60).mirror().addBox(1.5F, -2.0F, -9.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 7.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition armor_right_arm = right_arm.addOrReplaceChild("armor_right_arm", CubeListBuilder.create().texOffs(56, 73).addBox(-3.0F, -2.0F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(73, 9).addBox(-3.0F, 6.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = armor_right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(68, 37).addBox(-8.0F, -28.0F, 6.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(74, 42).addBox(-8.0F, -28.0F, 5.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 26).addBox(-3.95F, -28.225F, 4.5F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(44, 74).addBox(-8.0F, -28.0F, 10.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 20.75F, -8.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(52, 17).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition armor_left_arm = left_arm.addOrReplaceChild("armor_left_arm", CubeListBuilder.create().texOffs(0, 70).addBox(1.0F, -2.0F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(68, 73).addBox(1.0F, 6.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r8 = armor_left_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(68, 21).addBox(3.5F, -28.0F, 6.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(3.5F, -28.0F, 5.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 10).addBox(2.95F, -28.225F, 4.5F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(73, 26).addBox(3.5F, -28.0F, 10.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 20.75F, -8.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(66, 0).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                .texOffs(12, 76).addBox(-2.15F, 6.5F, -2.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition cube_r9 = right_boot.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-2.896F, 9.4741F, 0.6055F, 0.2503F, -0.4928F, 0.2503F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(20, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(44, 65).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F))
                .texOffs(22, 76).addBox(-1.9F, 6.5F, -2.49F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.3F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition cube_r10 = left_boot.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.0F, -1.5F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(2.8077F, 9.2467F, 0.551F, 0.2503F, 0.4928F, -0.2503F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
}
