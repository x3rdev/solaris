package com.github.x3rmination.solaris.common.item;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import yesman.epicfight.skill.Skill;

public interface SolarisWeapon {

    default void serverAttack(ServerPlayer serverPlayer, Skill skill) throws NoSuchMethodException {

    }

    default void clientAttack(LocalPlayer localPlayer, Skill skill) throws NoSuchMethodException {

    }
}
