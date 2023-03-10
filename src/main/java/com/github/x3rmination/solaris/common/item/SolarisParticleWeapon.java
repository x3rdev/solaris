package com.github.x3rmination.solaris.common.item;

import com.mojang.math.Vector3f;
import net.minecraft.core.particles.ParticleOptions;

import java.util.Random;

public interface SolarisParticleWeapon {

    static final Random PARTRAND = new Random();

    Vector3f[] getParticles();

    ParticleOptions getParticleType();

    default int getParticleDelay() {
        return 2;
    }
}
