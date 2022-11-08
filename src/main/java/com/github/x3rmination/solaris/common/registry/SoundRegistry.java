package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Solaris.MOD_ID);

    public static final RegistryObject<SoundEvent> CHAINSAW_HIT = SOUND_EVENTS.register("entity.hit.chainsaw",
            () -> new SoundEvent(new ResourceLocation(Solaris.MOD_ID, "entity.hit.chainsaw")));
}
