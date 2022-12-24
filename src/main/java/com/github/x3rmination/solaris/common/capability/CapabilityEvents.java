package com.github.x3rmination.solaris.common.capability;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEvents {

    public static void registerCaps(RegisterCapabilitiesEvent event) {
//        event.register(RaceCapability.class);
    }

    @SubscribeEvent
    public static void joinWorld(EntityJoinWorldEvent event) {

    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
//        if(event.isWasDeath()) {
//            event.getOriginal().getCapability(RaceCapability.INSTANCE).ifPresent(oldStore -> {
//                event.getOriginal().getCapability(RaceCapability.INSTANCE).ifPresent(newStore -> {
//                    newStore.setRace(oldStore.getRace());
//                });
//            });
//        }
    }
}
