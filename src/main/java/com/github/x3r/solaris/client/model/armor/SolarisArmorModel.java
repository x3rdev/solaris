package com.github.x3r.solaris.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class SolarisArmorModel extends HumanoidModel<LivingEntity> {

    public final ModelPart rightBoot;
    public final ModelPart leftBoot;
    public SolarisArmorModel(ModelPart pRoot) {
        super(pRoot);
        this.rightBoot = pRoot.getChild("right_boot");
        this.leftBoot = pRoot.getChild("left_boot");
    }

    @Override
    public void setAllVisible(boolean pVisible) {
        this.rightBoot.visible = pVisible;
        this.leftBoot.visible = pVisible;
        super.setAllVisible(pVisible);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightBoot.copyFrom(rightLeg);
        leftBoot.copyFrom(leftLeg);
        rightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public List<ModelPart> getParts() {
        return List.of(this.head, this.hat, this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.rightBoot, this.leftBoot);
    }
}
