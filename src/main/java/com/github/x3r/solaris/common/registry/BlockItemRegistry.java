package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockItemRegistry {

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Solaris.MOD_ID);

    public static final RegistryObject<BlockItem> BRIMSTONE = BLOCK_ITEMS.register("brimstone",
            () -> new BlockItem(BlockRegistry.BRIMSTONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SCORCHED_DIRT = BLOCK_ITEMS.register("scorched_dirt",
            () -> new BlockItem(BlockRegistry.SCORCHED_DIRT.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SCORCHED_GRASS_BLOCK = BLOCK_ITEMS.register("scorched_grass_block",
            () -> new BlockItem(BlockRegistry.SCORCHED_GRASS_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SCORCHED_IRON_ORE = BLOCK_ITEMS.register("scorched_iron_ore",
            () -> new BlockItem(BlockRegistry.SCORCHED_IRON_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SCORCHED_LOG = BLOCK_ITEMS.register("scorched_log",
            () -> new BlockItem(BlockRegistry.SCORCHED_LOG.get(), new Item.Properties()));
}
