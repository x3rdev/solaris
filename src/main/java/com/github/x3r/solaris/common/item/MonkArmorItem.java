package com.github.x3r.solaris.common.item;

import com.github.x3r.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;

public class MonkArmorItem extends SolarisArmorItem {

    public MonkArmorItem(Type type, Properties builder) {
        super(ArmorMaterialRegistry.MONK, type, builder);
    }
}
