package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.item.DemonicArmorItem;
import com.github.x3r.solaris.common.item.DemonicAxe;
import com.github.x3r.solaris.common.item.StaffItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Solaris.MOD_ID);

    public static final RegistryObject<Item> DEMONIC_AXE = ITEMS.register("demonic_axe",
            () -> new DemonicAxe(new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> SCORCHED_BUG_SPAWN_EGG = ITEMS.register("scorched_bug_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.SCORCHED_BUG, 0x342d2d, 0xdf3e23, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> ELEMENTAL_SPAWN_EGG = ITEMS.register("elemental_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ELEMENTAL, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> SNOW_TROLL_SPAWN_EGG = ITEMS.register("snow_troll_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.SNOW_TROLL, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> URBOROS_SPAWN_EGG = ITEMS.register("urboros_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.URBOROS, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> URBOROS_EPHYRA_SPAWN_EGG = ITEMS.register("urboros_ephyra_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.URBOROS_EPHYRA, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<StaffItem> ELYRIUM_STAFF = ITEMS.register("elyrium_staff",
            () -> new StaffItem(new Item.Properties()));
    public static final RegistryObject<DemonicArmorItem> DEMONIC_HELMET = ITEMS.register("demonic_helmet",
            () -> new DemonicArmorItem(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<DemonicArmorItem> DEMONIC_CHESTPLATE = ITEMS.register("demonic_chestplate",
            () -> new DemonicArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<DemonicArmorItem> DEMONIC_LEGGINGS = ITEMS.register("demonic_leggings",
            () -> new DemonicArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<DemonicArmorItem> DEMONIC_BOOTS = ITEMS.register("demonic_boots",
            () -> new DemonicArmorItem(ArmorItem.Type.BOOTS, new Item.Properties()));

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

