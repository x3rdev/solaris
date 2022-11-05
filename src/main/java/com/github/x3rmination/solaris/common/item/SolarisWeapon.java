package com.github.x3rmination.solaris.common.item;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;

public interface SolarisWeapon {

    default void serverAttack(ServerPlayer serverPlayer) throws NoSuchMethodException {

    }

    default void clientAttack(LocalPlayer localPlayer) throws NoSuchMethodException {

    }
}
