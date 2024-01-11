package com.github.x3r.solaris.client;

import com.github.x3r.solaris.common.network.ActivateSolarisWeaponMessage;
import com.github.x3r.solaris.common.network.SolarisPacketHandler;
import com.github.x3r.solaris.common.registry.KeyRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyEvents {

    @SubscribeEvent
    public static void keyPressed(InputEvent.Key event) {
        if(KeyRegistry.keyActivateSolarisWeapon.isDown()) {
            SolarisPacketHandler.INSTANCE.sendToServer(new ActivateSolarisWeaponMessage());
        }
    }
}
