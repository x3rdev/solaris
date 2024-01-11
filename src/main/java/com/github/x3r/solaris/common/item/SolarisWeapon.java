package com.github.x3r.solaris.common.item;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import yesman.epicfight.skill.Skill;

public interface SolarisWeapon {

    default void serverAttack(ServerPlayer serverPlayer, Skill skill) {

    }

    default void clientAttack(LocalPlayer localPlayer, Skill skill) {

    }

    default void activateAbility(ServerPlayer serverPlayer) {

    }
}
