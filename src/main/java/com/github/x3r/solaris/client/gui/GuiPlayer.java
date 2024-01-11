package com.github.x3r.solaris.client.gui;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class GuiPlayer extends AbstractClientPlayer {

    private ResourceLocation skin;

    public GuiPlayer(ClientLevel pClientLevel, GameProfile pGameProfile, ResourceLocation skin) {
        super(pClientLevel, pGameProfile);
        this.skin = skin;
    }

    @Override
    public ResourceLocation getSkinTextureLocation() {
        return this.skin;
    }
}
