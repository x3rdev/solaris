package com.github.x3rmination.solaris.common.entity.attack.FireKatanaAttack;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FireKatanaAttackModel extends GeoModel<FireKatanaAttackEntity> {

    @Override
    public ResourceLocation getModelResource(FireKatanaAttackEntity animatable) {
        return new ResourceLocation(Solaris.MOD_ID, "geo/fire_katana_attack.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FireKatanaAttackEntity animatable) {
        int count = (int) (animatable.getLevel().nextSubTickCount()/20 % 32);
        return new ResourceLocation(Solaris.MOD_ID, "textures/entity/fire_katana_attack/fire_" + count + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(FireKatanaAttackEntity animatable) {
        return null;
    }
}
