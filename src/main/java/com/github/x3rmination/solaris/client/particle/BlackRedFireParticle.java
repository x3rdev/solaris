package com.github.x3rmination.solaris.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;

public class BlackRedFireParticle extends TextureSheetParticle {

    protected BlackRedFireParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return null;
    }
}
