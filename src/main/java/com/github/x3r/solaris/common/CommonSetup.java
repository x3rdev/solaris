package com.github.x3r.solaris.common;

import com.github.x3r.solaris.common.block.DungeonGateBlock;
import com.github.x3r.solaris.common.entity.*;
import com.github.x3r.solaris.common.network.SolarisPacketHandler;
import com.github.x3r.solaris.common.registry.DimensionRegistry;
import com.github.x3r.solaris.common.registry.EntityRegistry;
import com.github.x3r.solaris.common.registry.SkillsRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {

    public static void setup(final FMLCommonSetupEvent event) {
        SolarisPacketHandler.registerPackets();
        SkillsRegistry.registerSkills();
    }

    public static void attributeSetup(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.SCORCHED_BUG.get(), ScorchedBugEntity.createAttributes());
        event.put(EntityRegistry.ELEMENTAL.get(), ElementalEntity.createAttributes());
        event.put(EntityRegistry.SNOW_TROLL.get(), SnowTrollEntity.createAttributes());
        event.put(EntityRegistry.URBOROS.get(), UrborosEntity.createAttributes());
        event.put(EntityRegistry.URBOROS_EPHYRA.get(), UrborosEphyraEntity.createAttributes());
        event.put(EntityRegistry.ANCIENT_MECH_BOSS.get(), AncientMechBossEntity.createAttributes());
    }

    public static void spawnPlacementSetup(SpawnPlacementRegisterEvent event) {
        event.register(EntityRegistry.SCORCHED_BUG.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules,
                SpawnPlacementRegisterEvent.Operation.OR);
    }

}
