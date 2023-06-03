package com.github.x3rmination.solaris.common.item.MonkArmor;

import com.github.x3rmination.solaris.common.item.SolarisArmorItem;
import com.github.x3rmination.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;

public class MonkArmorItem extends SolarisArmorItem {

    public MonkArmorItem(EquipmentSlot slot, Properties builder) {
        super(ArmorMaterialRegistry.MONK, slot, builder);
    }
}
