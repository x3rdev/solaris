package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.block.SolarisSunBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Solaris.MOD_ID);

    public static final RegistryObject<BlockEntityType<SolarisSunBlockEntity>> SOLARIS_SUN_BLOCK_ENTITY = BLOCK_ENTITIES.register("solaris_sun_block_entity",
            () -> BlockEntityType.Builder.of(SolarisSunBlockEntity::new, BlockRegistry.SOLARIS_SUN.get()).build(null));
}
