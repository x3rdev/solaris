package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.item.GeckoBlockItem;
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
    public static final RegistryObject<BlockItem> SCORCHED_GRASS = BLOCK_ITEMS.register("scorched_grass",
            () -> new BlockItem(BlockRegistry.SCORCHED_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> URBOROS_EGGS = BLOCK_ITEMS.register("urboros_eggs",
            () -> new BlockItem(BlockRegistry.URBOROS_EGGS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE = BLOCK_ITEMS.register("snowy_stone",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE_STAIRS = BLOCK_ITEMS.register("snowy_stone_stairs",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE_SLAB = BLOCK_ITEMS.register("snowy_stone_slab",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE_PRESSURE_PLATE = BLOCK_ITEMS.register("snowy_stone_pressure_plate",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE_BRICKS = BLOCK_ITEMS.register("snowy_stone_bricks",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE_BRICK_STAIRS = BLOCK_ITEMS.register("snowy_stone_brick_stairs",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE_BRICK_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_STONE_BRICK_SLAB = BLOCK_ITEMS.register("snowy_stone_brick_slab",
            () -> new BlockItem(BlockRegistry.SNOWY_STONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> PERMAFROST = BLOCK_ITEMS.register("permafrost",
            () -> new BlockItem(BlockRegistry.PERMAFROST.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GRASSY_PERMAFROST = BLOCK_ITEMS.register("grassy_permafrost",
            () -> new BlockItem(BlockRegistry.GRASSY_PERMAFROST.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SNOWY_GRASS = BLOCK_ITEMS.register("snowy_grass",
            () -> new BlockItem(BlockRegistry.SNOWY_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> URBOROS_POLYP = BLOCK_ITEMS.register("urboros_polyp",
            () -> new GeckoBlockItem(BlockRegistry.URBOROS_POLYP.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> URBOROS_STROBILA = BLOCK_ITEMS.register("urboros_strobila",
            () -> new GeckoBlockItem(BlockRegistry.URBOROS_STROBILA.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> ANCIENT_GLOWING_BRICKS = BLOCK_ITEMS.register("ancient_glowing_bricks",
            () -> new GeckoBlockItem(BlockRegistry.ANCIENT_GLOWING_BRICKS.get(), new Item.Properties()));
}
