package com.github.x3rmination.solaris.common.capability.race;


import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class RaceCapability {

    private RaceCapability() {
    }

    public static final Capability<RaceCapabilityInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

}
