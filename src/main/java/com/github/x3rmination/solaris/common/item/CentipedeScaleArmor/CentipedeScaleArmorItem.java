package com.github.x3rmination.solaris.common.item.CentipedeScaleArmor;

import com.github.x3rmination.solaris.common.item.SolarisArmorItem;
import com.github.x3rmination.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.EquipmentSlot;

public class CentipedeScaleArmorItem extends SolarisArmorItem {

    public CentipedeScaleArmorItem(EquipmentSlot pSlot, Properties pProperties) {
        super(ArmorMaterialRegistry.CENTIPEDE_SCALE, pSlot, pProperties);
    }
}
