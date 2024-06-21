package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.block.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Solaris.MOD_ID);
    public static final RegistryObject<Block> BRIMSTONE = BLOCKS.register("brimstone",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SCORCHED_DIRT = BLOCKS.register("scorched_dirt",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> SCORCHED_GRASS_BLOCK = BLOCKS.register("scorched_grass_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SCORCHED_IRON_ORE = BLOCKS.register("scorched_iron_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SCORCHED_LOG = BLOCKS.register("scorched_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SCORCHED_GRASS = BLOCKS.register("scorched_grass",
            () -> new TallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> SNOWY_STONE = BLOCKS.register("snowy_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SNOWY_STONE_STAIRS = BLOCKS.register("snowy_stone_stairs",
            () -> new StairBlock(() -> SNOWY_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SNOWY_STONE.get())));
    public static final RegistryObject<Block> SNOWY_STONE_SLAB = BLOCKS.register("snowy_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(SNOWY_STONE.get())));
    public static final RegistryObject<Block> SNOWY_STONE_PRESSURE_PLATE = BLOCKS.register("snowy_stone_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(SNOWY_STONE.get()), BlockSetType.STONE));
    public static final RegistryObject<Block> SNOWY_STONE_BRICKS = BLOCKS.register("snowy_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SNOWY_STONE_BRICK_STAIRS = BLOCKS.register("snowy_stone_brick_stairs",
            () -> new StairBlock(() -> SNOWY_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SNOWY_STONE_BRICKS.get())));
    public static final RegistryObject<Block> SNOWY_STONE_BRICK_SLAB = BLOCKS.register("snowy_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(SNOWY_STONE_BRICKS.get())));
    public static final RegistryObject<Block> PERMAFROST = BLOCKS.register("permafrost",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> GRASSY_PERMAFROST = BLOCKS.register("grassy_permafrost",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SNOWY_GRASS = BLOCKS.register("snowy_grass",
            () -> new TallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> URBOROS_EGGS = BLOCKS.register("urboros_eggs",
            () -> new UrborosEggsBlock(BlockBehaviour.Properties.of().strength(2.0F, 0.2F).sound(SoundType.FROGSPAWN).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> URBOROS_POLYP = BLOCKS.register("urboros_polyp",
            () -> new UrborosPolypBlock(BlockBehaviour.Properties.of().strength(2.0F, 0.2F).sound(SoundType.FROGLIGHT).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> URBOROS_STROBILA = BLOCKS.register("urboros_strobila",
            () -> new UrborosStrobilaBlock(BlockBehaviour.Properties.of().strength(2.0F, 0.2F).sound(SoundType.FROGLIGHT).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ANCIENT_GLOWING_BRICKS = BLOCKS.register("ancient_glowing_bricks",
            () -> new AncientGlowingBricksBlock(BlockBehaviour.Properties.of().strength(1.5F, 6F)));
    public static final RegistryObject<Block> TROLL_CAVE_GATE = BLOCKS.register("troll_cave_gate",
            () -> new TrollCaveGateBlock(BlockBehaviour.Properties.of().strength(-1.0F, 3600000.0F).noLootTable()));

}
