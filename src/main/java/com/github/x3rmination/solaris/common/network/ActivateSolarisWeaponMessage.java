package com.github.x3rmination.solaris.common.network;

import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ActivateSolarisWeaponMessage {

    public ActivateSolarisWeaponMessage() {
    }

    public ActivateSolarisWeaponMessage(FriendlyByteBuf buf) {

    }

    public void encode(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer sender = context.get().getSender();
            if (sender != null && sender.getMainHandItem().getItem() instanceof SolarisWeapon solarisWeapon) {
                solarisWeapon.activateAbility(sender);
            }
        });
        context.get().setPacketHandled(true);
    }
}
