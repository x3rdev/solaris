package com.github.x3r.solaris.common.network;

import com.github.x3r.solaris.client.network.SendParticleClient;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendParticleMessage {

    private ParticleOptions pParticleData;
    private final double pX;
    private final double pY;
    private final double pZ;
    private final double pXSpeed;
    private final double pYSpeed;
    private final double pZSpeed;

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
        ParticleType<?> particleType = BuiltInRegistries.PARTICLE_TYPE.byId(buf.readInt());
        this.pX = buf.readDouble();
        this.pY = buf.readDouble();
        this.pZ = buf.readDouble();
        this.pXSpeed = buf.readDouble();
        this.pYSpeed = buf.readDouble();
        this.pZSpeed = buf.readDouble();
        this.pParticleData = this.readParticle(buf, particleType);
    }

    private <T extends ParticleOptions> T readParticle(FriendlyByteBuf pBuffer, ParticleType<T> pParticleType) {
        return pParticleType.getDeserializer().fromNetwork(pParticleType, pBuffer);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(BuiltInRegistries.PARTICLE_TYPE.getId(this.pParticleData.getType()));
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

    public ParticleOptions getParticleData() {
        return pParticleData;
    }

    public double getX() {
        return pX;
    }

    public double getY() {
        return pY;
    }

    public double getZ() {
        return pZ;
    }

    public double getXSpeed() {
        return pXSpeed;
    }

    public double getYSpeed() {
        return pYSpeed;
    }

    public double getZSpeed() {
        return pZSpeed;
    }
}
