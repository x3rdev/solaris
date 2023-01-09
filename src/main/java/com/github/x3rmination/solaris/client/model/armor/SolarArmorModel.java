package com.github.x3rmination.solaris.client.model.armor;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class SolarArmorModel extends HumanoidModel<LivingEntity> {
    public static final ModelLayerLocation INNER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Solaris.MOD_ID, "solar_armor"), "inner");
    public static final ModelLayerLocation OUTER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Solaris.MOD_ID, "solar_armor"), "outer");


    public SolarArmorModel(ModelPart pRoot) {
        super(pRoot);
    }

    public static LayerDefinition createBodyLayer(CubeDeformation deformation, float offset) {
        MeshDefinition meshdefinition = createMesh(deformation, offset);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition bipedHead = partdefinition.getChild("head");
        PartDefinition bipedBody = partdefinition.getChild("body");
        PartDefinition bipedRightArm = partdefinition.getChild("right_arm");
        PartDefinition bipedLeftArm = partdefinition.getChild("left_arm");
        PartDefinition bipedRightLeg = partdefinition.getChild("right_leg");
        PartDefinition bipedLeftLeg = partdefinition.getChild("left_leg");

        PartDefinition armorHead = bipedHead.addOrReplaceChild("armorHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = armorHead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(160, 15).addBox(0.25F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -5.1533F, 1.7706F, 0.3981F, 0.1611F, 0.0674F));

        PartDefinition cube_r2 = armorHead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(128, 58).addBox(-0.25F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -5.1533F, 1.7706F, 0.3981F, -0.1611F, -0.0674F));

        PartDefinition cube_r3 = armorHead.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(172, 21).addBox(4.025F, -4.0F, -5.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(176, 9).addBox(-5.05F, -4.0F, -5.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r4 = armorHead.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(160, 0).addBox(-5.0F, -1.5F, -1.0F, 10.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition armorBody = bipedBody.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(154, 10).addBox(-4.0F, 1.5F, -3.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(196, 51).addBox(2.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(200, 62).addBox(-3.0F, 6.5F, -3.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(192, 43).addBox(-4.3F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(200, 59).addBox(-3.0F, 6.5F, 2.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = armorBody.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(128, 81).addBox(-5.5F, -2.0F, -9.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 14.0F, 7.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r6 = armorBody.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(146, 81).addBox(1.5F, -2.0F, -9.5F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 14.0F, 7.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition armorRightArm = bipedRightArm.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(184, 73).addBox(-4.31F, -2.25F, -2.35F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(201, 9).addBox(-4.31F, 6.25F, -2.35F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = armorRightArm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(196, 37).addBox(-8.5F, -28.0F, 6.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(202, 42).addBox(-8.5F, -28.0F, 5.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(192, 26).addBox(-4.0F, -28.5F, 4.5F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(172, 74).addBox(-8.5F, -28.0F, 10.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 20.75F, -8.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition armorLeftArm = bipedLeftArm.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(128, 70).addBox(10.16F, -2.25F, -2.325F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(196, 73).addBox(10.16F, 6.25F, -2.325F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 0.0F, 0.0F));

        PartDefinition cube_r8 = armorLeftArm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(196, 21).addBox(3.0F, -28.0F, 6.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(152, 0).addBox(3.0F, -28.0F, 5.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(192, 10).addBox(2.975F, -28.5F, 4.5F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(201, 26).addBox(3.0F, -28.0F, 10.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 20.75F, -8.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition armorLeftLeg = bipedLeftLeg.addOrReplaceChild("armorLeftLeg", CubeListBuilder.create(), PartPose.offset(4.0F, 6.0F, 0.0F));

        PartDefinition armorLeftBoot = bipedLeftLeg.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create().texOffs(150, 76).addBox(-1.825F, 5.9F, -3F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r9 = armorLeftBoot.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(128, 12).mirror().addBox(-4F, -2.25F, -2.75F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 9.5F, 1.0F, 0.2503F, 0.4928F, -0.2503F));

        PartDefinition armorRightLeg = bipedRightLeg.addOrReplaceChild("armorRightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorRightBoot = bipedRightLeg.addOrReplaceChild("armorRightBoot", CubeListBuilder.create().texOffs(140, 76).mirror().addBox(-2.125F, 5.9F, -3F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r10 = armorRightBoot.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(128, 12).addBox(2.125F, -2.25F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6F, 9.5F, 1.0F, 0.2503F, -0.4928F, 0.2503F));

        return LayerDefinition.create(meshdefinition, 256, 128);
    }
}
