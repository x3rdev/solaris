package com.github.x3rmination.solaris.client.network;

import com.github.x3rmination.solaris.common.network.SendParticleMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendParticleClient {

    public static void handlePacket(SendParticleMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
                Minecraft.getInstance().particleEngine.createParticle(msg.getpParticleData(), msg.getpX(), msg.getpY(), msg.getpZ(), msg.getpXSpeed(), msg.getpYSpeed(), msg.getpZSpeed());
        });
    }
}
