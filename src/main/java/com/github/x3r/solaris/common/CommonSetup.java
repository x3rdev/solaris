package com.github.x3r.solaris.common;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.DimensionEffects;
import com.github.x3r.solaris.common.entity.ScorchedBugEntity;
import com.github.x3r.solaris.common.network.SolarisPacketHandler;
import com.github.x3r.solaris.common.registry.EntityRegistry;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {

    public static void setup(final FMLCommonSetupEvent event) {
        SolarisPacketHandler.registerPackets();
    }

    public static void attributeSetup(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.SCORCHED_BUG.get(), ScorchedBugEntity.createAttributes());
    }

    public static void spawnPlacementSetup(SpawnPlacementRegisterEvent event) {
        event.register(EntityRegistry.SCORCHED_BUG.get(),
                SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.OR);
    }

}
