package com.github.x3rmination.solaris.client.render.item;

import com.github.x3rmination.solaris.client.model.armor.CentipedeScaleArmorModel;
import com.github.x3rmination.solaris.client.model.armor.MonkArmorModel;
import com.github.x3rmination.solaris.common.item.CentipedeScaleArmor.CentipedeScaleArmorItem;
import com.github.x3rmination.solaris.common.item.MonkArmor.MonkArmorItem;
import com.github.x3rmination.solaris.common.item.SolarArmor.SolarArmorItem;
import com.github.x3rmination.solaris.client.model.armor.SolarArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

public class SolarisArmorRenderProperties implements IItemRenderProperties {

    private static boolean init;
    public static SolarArmorModel SOLAR_ARMOR_MODEL;
    public static CentipedeScaleArmorModel CENTIPEDE_SCALE_ARMOR_MODEL;
    public static MonkArmorModel MONK_ARMOR_MODEL;

    @Nullable
    @Override
    public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if(!init) {
            initModels();
        }
        Item item = itemStack.getItem();
        if(item instanceof SolarArmorItem) {
            return SOLAR_ARMOR_MODEL;
        }
        if(item instanceof CentipedeScaleArmorItem) {
            return CENTIPEDE_SCALE_ARMOR_MODEL;
        }
        if(item instanceof MonkArmorItem) {
            return MONK_ARMOR_MODEL;
        }
        return _default;
    }

    public void initModels() {
        init = true;
        SOLAR_ARMOR_MODEL = new SolarArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(SolarArmorModel.LAYER_LOCATION));
        CENTIPEDE_SCALE_ARMOR_MODEL = new CentipedeScaleArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(CentipedeScaleArmorModel.LAYER_LOCATION));
        MONK_ARMOR_MODEL = new MonkArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(MonkArmorModel.LAYER_LOCATION));

    }
}
