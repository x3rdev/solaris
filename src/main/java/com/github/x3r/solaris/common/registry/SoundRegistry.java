package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Solaris.MOD_ID);

    public static final RegistryObject<SoundEvent> SCORCHED_BUG_HURT = registerSound("scorched_bug_hurt");
    public static final RegistryObject<SoundEvent> SCORCHED_BUG_DEATH = registerSound("scorched_bug_death");
    public static final RegistryObject<SoundEvent> SCORCHED_BUG_STEP = registerSound("scorched_bug_step");
    public static final RegistryObject<SoundEvent> SCORCHED_BUG_ENTER_BALL = registerSound("scorched_bug_enter_ball");
    public static final RegistryObject<SoundEvent> SCORCHED_BUG_EXIT_BALL = registerSound("scorched_bug_exit_ball");

    public static  RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Solaris.MOD_ID, name)));
    }
}
