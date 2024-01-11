package com.github.x3r.solaris.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class DimensionEffects {
    public static class SolarisEffects extends DimensionSpecialEffects {
        public SolarisEffects() {
            super(0, false, DimensionSpecialEffects.SkyType.END, false, false);
        }

        public Vec3 getBrightnessDependentFogColor(Vec3 p_108894_, float p_108895_) {
            return p_108894_.scale(0.15F);
        }

        public boolean isFoggyAt(int p_108891_, int p_108892_) {
            return true;
        }

        @Nullable
        public float[] getSunriseColor(float p_108888_, float p_108889_) {
            return null;
        }

        @Override
        public SkyType skyType() {
            return SkyType.END;
        }


    }
}
