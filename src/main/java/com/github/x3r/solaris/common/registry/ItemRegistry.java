package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.item.CentipedeScaleArmor.CentipedeScaleArmorItem;
import com.github.x3r.solaris.common.item.CentipedeScaleArmor.WhitestoneArmorItem;
import com.github.x3r.solaris.common.item.MonkArmorItem;
import com.github.x3r.solaris.common.item.SolarArmorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Solaris.MOD_ID);

    public static final RegistryObject<Item> SOLARIS_SUN = ITEMS.register("solaris",
            () -> new Item((new Item.Properties())));
    public static final RegistryObject<Item> SOLARIS_SUN_AURA = ITEMS.register("solaris_aura",
            () -> new Item((new Item.Properties())));

    public static final RegistryObject<Item> SOLAR_HELMET = ITEMS.register("solar_helmet",
            () -> new SolarArmorItem(ArmorItem.Type.HELMET, (new Item.Properties())));

    public static final RegistryObject<Item> SOLAR_CHESTPLATE = ITEMS.register("solar_chestplate",
            () -> new SolarArmorItem(ArmorItem.Type.CHESTPLATE, (new Item.Properties())));

    public static final RegistryObject<Item> SOLAR_LEGGINGS = ITEMS.register("solar_leggings",
            () -> new SolarArmorItem(ArmorItem.Type.LEGGINGS, (new Item.Properties())));

    public static final RegistryObject<Item> SOLAR_BOOTS = ITEMS.register("solar_boots",
            () -> new SolarArmorItem(ArmorItem.Type.BOOTS, (new Item.Properties())));


    public static final RegistryObject<Item> CENTIPEDE_SCALE_HELMET = ITEMS.register("centipede_scale_helmet",
            () -> new CentipedeScaleArmorItem(ArmorItem.Type.HELMET, (new Item.Properties())));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_CHESTPLATE = ITEMS.register("centipede_scale_chestplate",
            () -> new CentipedeScaleArmorItem(ArmorItem.Type.CHESTPLATE, (new Item.Properties())));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_LEGGINGS = ITEMS.register("centipede_scale_leggings",
            () -> new CentipedeScaleArmorItem(ArmorItem.Type.LEGGINGS, (new Item.Properties())));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_BOOTS = ITEMS.register("centipede_scale_boots",
            () -> new CentipedeScaleArmorItem(ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<Item> MONK_HELMET = ITEMS.register("monk_helmet",
            () -> new MonkArmorItem(ArmorItem.Type.HELMET, (new Item.Properties())));

    public static final RegistryObject<Item> MONK_CHESTPLATE = ITEMS.register("monk_chestplate",
            () -> new MonkArmorItem(ArmorItem.Type.CHESTPLATE, (new Item.Properties())));

    public static final RegistryObject<Item> MONK_LEGGINGS = ITEMS.register("monk_leggings",
            () -> new MonkArmorItem(ArmorItem.Type.LEGGINGS, (new Item.Properties())));

    public static final RegistryObject<Item> MONK_BOOTS = ITEMS.register("monk_boots",
            () -> new MonkArmorItem(ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<Item> WHITESTONE_HELMET = ITEMS.register("whitestone_helmet",
            () -> new WhitestoneArmorItem(ArmorItem.Type.HELMET, (new Item.Properties())));

    public static final RegistryObject<Item> WHITESTONE_CHESTPLATE = ITEMS.register("whitestone_chestplate",
            () -> new WhitestoneArmorItem(ArmorItem.Type.CHESTPLATE, (new Item.Properties())));

    public static final RegistryObject<Item> WHITESTONE_LEGGINGS = ITEMS.register("whitestone_leggings",
            () -> new WhitestoneArmorItem(ArmorItem.Type.LEGGINGS, (new Item.Properties())));

    public static final RegistryObject<Item> WHITESTONE_BOOTS = ITEMS.register("whitestone_boots",
            () -> new WhitestoneArmorItem(ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static class ModItemTab {

        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Solaris.MOD_ID);

        public static final RegistryObject<CreativeModeTab> SOLARIS_ITEM_TAB = CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
                .icon(Items.NAME_TAG::getDefaultInstance)
                .title(Component.literal("itemGroup."+Solaris.MOD_ID))
                .displayItems((displayParameters, output) -> {
                    ItemRegistry.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
                    BlockItemRegistry.BLOCK_ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));
                })
                .build());
    }
}

