package com.github.x3rmination.solaris.common.item.DemonsBlade.gui;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.solaris.player_race.PlayerRace;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.PlayerModelPart;

public class DisplayPlayer extends RemotePlayer {

    private PlayerRace race;

    public DisplayPlayer(ClientLevel pClientLevel, GameProfile pGameProfile, PlayerRace race) {
        super(pClientLevel, pGameProfile);
        this.race = race;
    }

    @Override
    public ResourceLocation getSkinTextureLocation() {
        return new ResourceLocation(Solaris.MOD_ID, "textures/entity/" + this.race.toString() + ".png");
    }

    @Override
    public boolean isModelPartShown(PlayerModelPart pPart) {
        return true;
    }
}
