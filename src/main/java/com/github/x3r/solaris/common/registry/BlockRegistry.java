package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.block.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
            () -> new ScorchedDirtBlock(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL).emissiveRendering((pState, pLevel, pPos) -> true)));
    public static final RegistryObject<Block> SCORCHED_GRASS_BLOCK = BLOCKS.register("scorched_grass_block",
            () -> new ScorchedDirtBlock(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SCORCHED_IRON_ORE = BLOCKS.register("scorched_iron_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SCORCHED_LOG = BLOCKS.register("scorched_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SCORCHED_GRASS = BLOCKS.register("scorched_grass",
            () -> new TallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> URBOROS_EGGS = BLOCKS.register("urboros_eggs",
            () -> new UrborosEggsBlock(BlockBehaviour.Properties.of().strength(2.0F, 0.2F).sound(SoundType.FROGSPAWN).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> URBOROS_POLYP = BLOCKS.register("urboros_polyp",
            () -> new UrborosPolypBlock(BlockBehaviour.Properties.of().strength(2.0F, 0.2F).sound(SoundType.FROGLIGHT).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> URBOROS_STROBILA = BLOCKS.register("urboros_strobila",
            () -> new UrborosStrobilaBlock(BlockBehaviour.Properties.of().strength(2.0F, 0.2F).sound(SoundType.FROGLIGHT).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ANCIENT_GLOWING_BRICKS = BLOCKS.register("ancient_glowing_bricks",
            () -> new AncientGlowingBricksBlock(BlockBehaviour.Properties.of().strength(1.5F, 6F)));
}
