package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.block.PearlstoneBlock;
import com.github.x3rmination.solaris.common.block.PearlstoneSlabs;
import com.github.x3rmination.solaris.common.block.PearlstoneStairs;
import com.github.x3rmination.solaris.common.block.SolarisSunBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Solaris.MOD_ID);

    public static final RegistryObject<Block> CHISELED_PEARLSTONE_BRICKS = BLOCKS.register("chiseled_pearlstone_bricks",
            () -> new PearlstoneBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> CRACKED_PEARLSTONE_BRICKS = BLOCKS.register("cracked_pearlstone_bricks",
            () -> new PearlstoneBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> PEARLSTONE = BLOCKS.register("pearlstone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> PEARLSTONE_BRICKS = BLOCKS.register("pearlstone_bricks",
            () -> new PearlstoneBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> SMOOTH_PEARLSTONE = BLOCKS.register("smooth_pearlstone",
            () -> new PearlstoneBlock(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> PEARLSTONE_STAIRS = BLOCKS.register("pearlstone_stairs",
            () -> new PearlstoneStairs(() -> PEARLSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> SMOOTH_PEARLSTONE_SLABS = BLOCKS.register("smooth_pearlstone_slabs",
            () -> new PearlstoneSlabs(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<Block> SOLARIS_SUN = BLOCKS.register("solaris_sun",
            () -> new SolarisSunBlock(BlockBehaviour.Properties.of(Material.STONE).emissiveRendering((pState, pLevel, pPos) -> true).lightLevel(value -> 15)));
}
