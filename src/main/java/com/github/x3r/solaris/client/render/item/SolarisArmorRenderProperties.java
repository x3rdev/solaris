package com.github.x3r.solaris.client.render.item;

import com.github.x3r.solaris.client.model.armor.CentipedeScaleArmorModel;
import com.github.x3r.solaris.client.model.armor.MonkArmorModel;
import com.github.x3r.solaris.client.model.armor.SolarArmorModel;
import com.github.x3r.solaris.client.model.armor.WhitestoneArmorModel;
import com.github.x3r.solaris.common.item.CentipedeScaleArmor.CentipedeScaleArmorItem;
import com.github.x3r.solaris.common.item.MonkArmorItem;
import com.github.x3r.solaris.common.item.SolarArmorItem;
import com.github.x3r.solaris.common.item.CentipedeScaleArmor.WhitestoneArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class SolarisArmorRenderProperties implements IClientItemExtensions {

    private static boolean init;
    public static SolarArmorModel SOLAR_ARMOR_MODEL;
    public static CentipedeScaleArmorModel CENTIPEDE_SCALE_ARMOR_MODEL;
    public static MonkArmorModel MONK_ARMOR_MODEL;
    public static WhitestoneArmorModel WHITESTONE_ARMOR_MODEL;

    @Override
    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
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
        if(item instanceof WhitestoneArmorItem) {
            return WHITESTONE_ARMOR_MODEL;
        }
        return original;
    }

    public void initModels() {
        init = true;
        SOLAR_ARMOR_MODEL = new SolarArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(SolarArmorModel.LAYER_LOCATION));
        CENTIPEDE_SCALE_ARMOR_MODEL = new CentipedeScaleArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(CentipedeScaleArmorModel.LAYER_LOCATION));
        MONK_ARMOR_MODEL = new MonkArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(MonkArmorModel.LAYER_LOCATION));
        WHITESTONE_ARMOR_MODEL = new WhitestoneArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(WhitestoneArmorModel.LAYER_LOCATION));
    }
}
