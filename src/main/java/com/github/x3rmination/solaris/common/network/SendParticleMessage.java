package com.github.x3rmination.solaris.common.network;

import com.github.x3rmination.solaris.client.network.SendParticleClient;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendParticleMessage {

    private ParticleOptions pParticleData;
    private double pX;
    private double pY;
    private double pZ;
    private double pXSpeed;
    private double pYSpeed;
    private double pZSpeed;

    public SendParticleMessage(ParticleOptions pParticleData, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        this.pParticleData = pParticleData;
        this.pX = pX;
        this.pY = pY;
        this.pZ = pZ;
        this.pXSpeed = pXSpeed;
        this.pYSpeed = pYSpeed;
        this.pZSpeed = pZSpeed;
    }

    public SendParticleMessage(FriendlyByteBuf buf) {
        ParticleType<?> particleType = Registry.PARTICLE_TYPE.byId(buf.readInt());
        this.pX = buf.readDouble();
        this.pY = buf.readDouble();
        this.pZ = buf.readDouble();
        this.pXSpeed = buf.readFloat();
        this.pYSpeed = buf.readFloat();
        this.pZSpeed = buf.readFloat();
        this.pParticleData = this.readParticle(buf, particleType);
    }

    private <T extends ParticleOptions> T readParticle(FriendlyByteBuf pBuffer, ParticleType<T> pParticleType) {
        return pParticleType.getDeserializer().fromNetwork(pParticleType, pBuffer);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(Registry.PARTICLE_TYPE.getId(this.pParticleData.getType()));
        buf.writeDouble(pX);
        buf.writeDouble(pY);
        buf.writeDouble(pZ);
        buf.writeDouble(pXSpeed);
        buf.writeDouble(pYSpeed);
        buf.writeDouble(pZSpeed);
        this.pParticleData.writeToNetwork(buf);
    }

    public void messageConsumer(Supplier<NetworkEvent.Context> ctx) {
        handle(new SendParticleMessage(this.pParticleData, this.pX, this.pY, this.pZ, this.pXSpeed, this.pYSpeed, this.pZSpeed), ctx);
    }

    public void handle(SendParticleMessage msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SendParticleClient.handlePacket(msg, context));
        });
        context.get().setPacketHandled(true);
    }

    public ParticleOptions getpParticleData() {
        return pParticleData;
    }

    public double getpX() {
        return pX;
    }

    public double getpY() {
        return pY;
    }

    public double getpZ() {
        return pZ;
    }

    public double getpXSpeed() {
        return pXSpeed;
    }

    public double getpYSpeed() {
        return pYSpeed;
    }

    public double getpZSpeed() {
        return pZSpeed;
    }
}
