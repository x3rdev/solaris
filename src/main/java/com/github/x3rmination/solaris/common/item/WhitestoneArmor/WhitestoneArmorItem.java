package com.github.x3rmination.solaris.common.item.WhitestoneArmor;

import com.github.x3rmination.solaris.common.item.SolarisArmorItem;
import com.github.x3rmination.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;

public class WhitestoneArmorItem extends SolarisArmorItem {

    public WhitestoneArmorItem(EquipmentSlot slot, Properties builder) {
        super(ArmorMaterialRegistry.WHITESTONE, slot, builder);
    }
}
