package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockItemRegistry {

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Solaris.MOD_ID);

    public static final RegistryObject<BlockItem> CHISELED_PEARLSTONE_BRICKS = BLOCK_ITEMS.register("chiseled_pearlstone_bricks",
            () -> new BlockItem(BlockRegistry.CHISELED_PEARLSTONE_BRICKS.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
    public static final RegistryObject<BlockItem> CRACKED_PEARLSTONE_BRICKS = BLOCK_ITEMS.register("cracked_pearlstone_bricks",
            () -> new BlockItem(BlockRegistry.CRACKED_PEARLSTONE_BRICKS.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
    public static final RegistryObject<BlockItem> PEARLSTONE = BLOCK_ITEMS.register("pearlstone",
            () -> new BlockItem(BlockRegistry.PEARLSTONE.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
    public static final RegistryObject<BlockItem> PEARLSTONE_BRICKS = BLOCK_ITEMS.register("pearlstone_bricks",
            () -> new BlockItem(BlockRegistry.PEARLSTONE_BRICKS.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
    public static final RegistryObject<BlockItem> SMOOTH_PEARLSTONE = BLOCK_ITEMS.register("smooth_pearlstone",
            () -> new BlockItem(BlockRegistry.SMOOTH_PEARLSTONE.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
    public static final RegistryObject<BlockItem> PEARLSTONE_STAIRS = BLOCK_ITEMS.register("pearlstone_stairs",
            () -> new BlockItem(BlockRegistry.PEARLSTONE_STAIRS.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
    public static final RegistryObject<BlockItem> SMOOTH_PEARLSTONE_SLABS = BLOCK_ITEMS.register("smooth_pearlstone_slabs",
            () -> new BlockItem(BlockRegistry.SMOOTH_PEARLSTONE_SLABS.get(), new Item.Properties().tab(ItemRegistry.ModItemTab.instance)));
}
