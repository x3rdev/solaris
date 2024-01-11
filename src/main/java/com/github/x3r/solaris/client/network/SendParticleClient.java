package com.github.x3r.solaris.client.network;

import com.github.x3r.solaris.common.network.SendParticleMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendParticleClient {

    public static void handlePacket(SendParticleMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
                Minecraft.getInstance().particleEngine.createParticle(msg.getParticleData(), msg.getX(), msg.getY(), msg.getZ(), msg.getXSpeed(), msg.getYSpeed(), msg.getZSpeed());
        });
    }
}
