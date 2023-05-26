package com.github.x3rmination.solaris.common.entity.attack.WaterFlowerAttack;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WaterFlowerAttackModel extends AnimatedGeoModel<WaterFlowerAttackEntity> {

    @Override
    public ResourceLocation getModelLocation(WaterFlowerAttackEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/water_flower_skill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WaterFlowerAttackEntity object) {
        int count = 0;
        return new ResourceLocation(Solaris.MOD_ID, "textures/entity/water_flower_attack/water_attack" + count + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WaterFlowerAttackEntity animatable) {
        return null;
    }
}
