package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import net.minecraft.client.KeyMapping;

public class KeyRegistry {

    public static final KeyMapping keyActivateSolarisWeapon = register(new KeyMapping("key." + Solaris.MOD_ID + ".activate_solaris_weapon", /* H */ 72, KeyMapping.CATEGORY_GAMEPLAY));

    public static KeyMapping register(KeyMapping keyMapping) {
        return keyMapping;
    }
}
