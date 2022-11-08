package com.github.x3rmination.solaris.common;

import com.github.x3rmination.solaris.common.registry.WeaponCapabilityRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;

public class CommonSetup {

    public static void setup(final FMLCommonSetupEvent event) {

    }

    public static void registerWeaponCapabilities(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put("chainsaw", WeaponCapabilityRegistry.CHAINSAW);
    }
}
