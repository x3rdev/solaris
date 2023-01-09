package com.github.x3rmination.solaris.client.render.item;

import com.github.x3rmination.solaris.common.item.SolarArmor.SolarArmorItem;
import com.github.x3rmination.solaris.client.model.armor.SolarArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

public class CustomArmorRenderProperties implements IItemRenderProperties {

    private static boolean init;
    public static SolarArmorModel SOLAR_ARMOR_MODEL_INNER;
    public static SolarArmorModel SOLAR_ARMOR_MODEL_OUTER;

    @Nullable
    @Override
    public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        boolean inner = armorSlot == EquipmentSlot.LEGS || armorSlot == EquipmentSlot.HEAD;
        if(!init) {
            initModels();
        }
        if(itemStack.getItem() instanceof SolarArmorItem) {
            return inner ? SOLAR_ARMOR_MODEL_INNER : SOLAR_ARMOR_MODEL_OUTER;
        }
        return _default;
    }

    public void initModels() {
        init = true;
        SOLAR_ARMOR_MODEL_INNER = new SolarArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(SolarArmorModel.INNER_LAYER_LOCATION));
        SOLAR_ARMOR_MODEL_OUTER = new SolarArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(SolarArmorModel.OUTER_LAYER_LOCATION));
    }
}
