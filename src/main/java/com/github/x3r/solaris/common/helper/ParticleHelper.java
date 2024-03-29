package com.github.x3r.solaris.common.helper;

import com.github.x3r.solaris.common.network.SendParticleMessage;
import com.github.x3r.solaris.common.network.SolarisPacketHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
public class ParticleHelper {

    private final Level level;
    private ParticleOptions particleOptions;
    private Vec3 pos;
    public ParticleHelper(Level level, ParticleOptions particleOptions, Vec3 pos) {
        this.level = level;
        this.particleOptions = particleOptions;
        this.pos = pos;
    }

    public void spawnCircle(double radius, int quality) {
        spawnCircle(radius, quality, new Vec3(0, 0, 0));
    }
    public void spawnCircle(double radius, int quality, Vec3 speed) {
        for(int i = 0; i < quality; i++) {
            double rad = Math.PI * 2;
            Vec3 newPos = new Vec3(Math.cos(rad * i / quality) * radius, 0, Math.sin(rad * i / quality) * radius);
            spawnParticle(newPos, speed);
        }
    }

    public void spawnArc(double radius, int quality, float xRot, float yRot) {
        spawnArc(radius, quality, xRot, yRot, new Vec3(0, 0, 0));
    }

    public void spawnArc(double radius, int quality, float xRot, float yRot, Vec3 speed) {
        yRot += 90;
        float mod = 90;
        float radianRot = (float) (yRot * Math.PI / 180);
        float radianXRot = (float) (xRot * Math.PI / 180);
//        spawnParticle(new Vec3(pos.x + (Math.cos(radianRot) * radius), pos.y - Math.sin(radianXRot) * radius, pos.z + (Math.sin(radianRot) * radius)), speed);
        for(int i = 0; i < quality; i++) {
            float radianMod = (float) ((mod * i / quality) * Math.PI / 180);
            spawnParticle(new Vec3(Math.cos(radianRot + radianMod) * radius, Math.sin(radianXRot) * radius * ((float) i / quality) * 2, Math.sin(radianRot + radianMod) * radius), speed);
            spawnParticle(new Vec3(Math.cos(radianRot - radianMod) * radius, Math.sin(radianXRot) * radius * ((float) i / quality) * 2, Math.sin(radianRot - radianMod) * radius), speed);
        }
    }

    public void spawnParticle(Vec3 pos, Vec3 speed) {
        if(this.level instanceof ClientLevel clientLevel) {
            spawnParticle(clientLevel, pos, speed);
        }
        if(this.level instanceof ServerLevel serverLevel) {
            spawnParticle(serverLevel, pos, speed);
        }
    }

    public void spawnParticle(ClientLevel clientLevel, Vec3 vec3, Vec3 speed) {
        clientLevel.addParticle(particleOptions, pos.x + vec3.x, pos.y + vec3.y, pos.z + vec3.z, speed.x, speed.y, speed.z);
    }

    public void spawnParticle(ServerLevel serverLevel, Vec3 vec3, Vec3 speed) {
        SolarisPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> (LevelChunk) serverLevel.getChunk(new BlockPos((int) pos.x, (int) pos.y, (int) pos.z))), new SendParticleMessage(particleOptions, pos.x + vec3.x, pos.y + vec3.y, pos.z + vec3.z, speed.x, speed.y, speed.z));
    }

    public Vec3 getPos() {
        return pos;
    }
    public void setPos(Vec3 newPos) {
        this.pos = newPos;
    }

    public ParticleOptions getParticleOptions() {
        return particleOptions;
    }
    public void setParticleOptions(ParticleOptions newParticleOptions) {
        this.particleOptions = newParticleOptions;
    }
}
