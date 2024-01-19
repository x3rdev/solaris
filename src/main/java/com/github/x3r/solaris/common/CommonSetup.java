package com.github.x3r.solaris.common;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.DimensionEffects;
import com.github.x3r.solaris.common.entity.ScorchedBugEntity;
import com.github.x3r.solaris.common.network.SolarisPacketHandler;
import com.github.x3r.solaris.common.registry.EntityRegistry;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {

    public static void setup(final FMLCommonSetupEvent event) {
        DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(Solaris.MOD_ID, "solaris"), new DimensionEffects.SolarisEffects());
        SolarisPacketHandler.registerPackets();
    }

    public static void attributeSetup(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.SCORCHED_BUG.get(), ScorchedBugEntity.createAttributes());
    }

}
