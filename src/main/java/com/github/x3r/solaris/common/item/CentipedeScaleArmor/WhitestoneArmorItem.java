package com.github.x3r.solaris.common.item.CentipedeScaleArmor;

import com.github.x3r.solaris.common.item.SolarisArmorItem;
import com.github.x3r.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;

public class WhitestoneArmorItem extends SolarisArmorItem {

    public WhitestoneArmorItem(Type type, Properties builder) {
        super(ArmorMaterialRegistry.WHITESTONE, type, builder);
    }
}
