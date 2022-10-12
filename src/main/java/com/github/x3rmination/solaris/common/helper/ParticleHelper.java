package com.github.x3rmination.solaris.common.helper;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class ParticleHelper {

    private final Level level;
    private final ParticleOptions particleOptions;
    private final Vec3 pos;
    public ParticleHelper(Level level, ParticleOptions particleOptions, Vec3 pos) {
        this.level = level;
        this.particleOptions = particleOptions;
        this.pos = pos;
    }
    public void spawnCircle(double radius, int quality) {
        for(int i = 0; i < quality; i++) {
            double rad = Math.PI * 2;
            Vec3 newPos = new Vec3(pos.x + Math.cos(rad * i / quality) * radius, pos.y, pos.z + Math.sin(rad * i / quality) * radius);
            spawnParticle(newPos, new Vec3(0,0,0));
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

    public void spawnParticle(ClientLevel clientLevel, Vec3 pos, Vec3 speed) {
        clientLevel.addParticle(particleOptions, pos.x, pos.y, pos.z, speed.x, speed.y, speed.z);
    }

    public void spawnParticle(ServerLevel serverLevel, Vec3 pos, Vec3 speed) {
        serverLevel.sendParticles(particleOptions, pos.x, pos.y, pos.z, 1, 0, 0, 0, speed.x);
    }
}
