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

    public static void spawnParticle(Level level, ParticleOptions particleOptions, Vec3 pos, Vec3 speed) {
        if(level instanceof ClientLevel clientLevel) {
            spawnParticle(clientLevel, particleOptions, pos, speed);
        }
        if(level instanceof ServerLevel serverLevel) {
            spawnParticle(serverLevel, particleOptions, pos, speed);
        }
    }

    public static void spawnParticle(ClientLevel clientLevel, ParticleOptions particleOptions, Vec3 pos, Vec3 speed) {
        clientLevel.addParticle(particleOptions, pos.x, pos.y, pos.z, speed.x, speed.y, speed.z);
    }

    public static void spawnParticle(ServerLevel serverLevel, ParticleOptions particleOptions, Vec3 pos, Vec3 speed) {
        SolarisPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() ->
                (LevelChunk) serverLevel.getChunk(new BlockPos((int) pos.x, (int) pos.y, (int) pos.z))),
                new SendParticleMessage(particleOptions, pos.x, pos.y, pos.z, speed.x, speed.y, speed.z));
    }
}
