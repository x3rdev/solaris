package com.github.x3rmination.solaris.common.item.SolarArmor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import software.bernie.example.item.GeckoArmorItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SolarArmorItem extends GeoArmorItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public SolarArmorItem(EquipmentSlot slot, Properties builder) {
        super(ArmorMaterials.DIAMOND, slot, builder);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
