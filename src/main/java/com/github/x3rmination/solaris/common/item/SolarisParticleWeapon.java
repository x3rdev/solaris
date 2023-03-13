package com.github.x3rmination.solaris.common.item;

import com.mojang.math.Vector3f;
import net.minecraft.core.particles.ParticleOptions;

import java.util.Random;

public interface SolarisParticleWeapon {

    static final Random PART_RAND = new Random();

    static final Vector3f[] GENERIC_PARTICLE_ARRAY = {
            new Vector3f(0, 0, 1.5F),
            new Vector3f(0, 0, 1.0F),
            new Vector3f(0, 0, 0.5F)
    };

    Vector3f[] getParticles();

    ParticleOptions getParticleType();

    default int getParticleDelay() {
        return 2;
    }
}
