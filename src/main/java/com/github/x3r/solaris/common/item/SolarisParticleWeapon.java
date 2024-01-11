package com.github.x3r.solaris.common.item;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public interface SolarisParticleWeapon {

    static final Random PART_RAND = new Random();

    static final Vec3[] GENERIC_PARTICLE_ARRAY = {
            new Vec3(0, 0, 1.5F),
            new Vec3(0, 0, 1.0F),
            new Vec3(0, 0, 0.5F)
    };

    Vec3[] getParticles();

    ParticleOptions getParticleType();

    default int getParticleDelay() {
        return 2;
    }
}
