package com.github.x3r.solaris.common.network;

import com.github.x3r.solaris.Solaris;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class SolarisPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(Solaris.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        int id = 0;
        INSTANCE.registerMessage(id++, ActivateSolarisWeaponMessage.class, ActivateSolarisWeaponMessage::encode, ActivateSolarisWeaponMessage::new, ActivateSolarisWeaponMessage::handle);
        INSTANCE.registerMessage(id++, SendParticleMessage.class, SendParticleMessage::encode, SendParticleMessage::new, SendParticleMessage::messageConsumer);
    }
}
