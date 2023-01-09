package com.github.x3rmination.solaris.common.item.SolarArmor;

import com.github.x3rmination.solaris.common.item.SolarisArmorItem;
import com.github.x3rmination.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;

public class SolarArmorItem extends SolarisArmorItem {
    public SolarArmorItem(EquipmentSlot slot, Properties builder) {
        super(ArmorMaterialRegistry.SOLAR_ARMOR, slot, builder);
    }
}
