package com.github.x3rmination.solaris.common.item.FireKatana;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FireKatanaAttackModel extends AnimatedGeoModel<FireKatanaAttackEntity> {

    @Override
    public ResourceLocation getModelLocation(FireKatanaAttackEntity object) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/fire_katana_attack.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FireKatanaAttackEntity object) {
        int count = (int) (object.getLevel().nextSubTickCount()/20 % 32);
        return new ResourceLocation(Solaris.MOD_ID, "textures/entity/fire_katana_attack/fire_" + count + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FireKatanaAttackEntity animatable) {
        return null;
    }
}
