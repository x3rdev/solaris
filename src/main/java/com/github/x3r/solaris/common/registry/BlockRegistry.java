package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Solaris.MOD_ID);
    public static final RegistryObject<Block> BRIMSTONE = BLOCKS.register("brimstone",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> SCORCHED_DIRT = BLOCKS.register("scorched_dirt",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GRAVEL).emissiveRendering((pState, pLevel, pPos) -> true)));
    public static final RegistryObject<Block> SCORCHED_GRASS_BLOCK = BLOCKS.register("scorched_grass_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> SCORCHED_IRON_ORE = BLOCKS.register("scorched_iron_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

}
