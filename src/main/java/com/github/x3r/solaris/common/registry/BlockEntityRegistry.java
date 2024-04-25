package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.block_entity.UrborosPolypBlockEntity;
import com.github.x3r.solaris.common.block_entity.UrborosStrobilaBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Solaris.MOD_ID);

    public static final RegistryObject<BlockEntityType<UrborosPolypBlockEntity>> URBOROS_POLYP = BLOCK_ENTITIES.register("urboros_polyp",
            () -> BlockEntityType.Builder.of(UrborosPolypBlockEntity::new, BlockRegistry.URBOROS_POLYP.get()).build(null));

    public static final RegistryObject<BlockEntityType<UrborosStrobilaBlockEntity>> URBOROS_STROBILA = BLOCK_ENTITIES.register("urboros_strobila",
            () -> BlockEntityType.Builder.of(UrborosStrobilaBlockEntity::new, BlockRegistry.URBOROS_STROBILA.get()).build(null));
}
