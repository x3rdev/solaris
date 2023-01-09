package com.github.x3rmination.solaris.client;

import com.github.x3rmination.solaris.common.network.ActivateSolarisWeaponMessage;
import com.github.x3rmination.solaris.common.network.SolarisPacketHandler;
import com.github.x3rmination.solaris.common.registry.KeyRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyEvents {

    @SubscribeEvent
    public static void keyPressed(InputEvent.KeyInputEvent event) {
        if(KeyRegistry.keyActivateSolarisWeapon.isDown()) {
            SolarisPacketHandler.INSTANCE.sendToServer(new ActivateSolarisWeaponMessage());
        }
    }
}
