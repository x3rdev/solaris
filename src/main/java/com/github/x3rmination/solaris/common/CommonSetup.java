package com.github.x3rmination.solaris.common;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.client.DimensionEffects;
import com.github.x3rmination.solaris.common.network.SolarisPacketHandler;
import com.github.x3rmination.solaris.common.registry.WeaponCapabilityRegistry;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;

public class CommonSetup {

    public static void setup(final FMLCommonSetupEvent event) {
        DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(Solaris.MOD_ID, "solaris"), new DimensionEffects.SolarisEffects());
        SolarisPacketHandler.registerPackets();
    }
    public static void registerEFMWeaponCaps(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put("chainsaw", WeaponCapabilityRegistry.CHAINSAW);
    }

}
