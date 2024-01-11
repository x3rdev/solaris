package com.github.x3r.solaris.common.item;

import com.github.x3r.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class SolarArmorItem extends SolarisArmorItem {
    public SolarArmorItem(ArmorItem.Type slot, Properties builder) {
        super(ArmorMaterialRegistry.SOLAR, slot, builder);
    }
}