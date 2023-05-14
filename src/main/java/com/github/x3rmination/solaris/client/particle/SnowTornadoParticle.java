package com.github.x3rmination.solaris.client.particle;

import com.mojang.math.Vector3d;
import com.mojang.math.Vector3f;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SnowTornadoParticle extends TextureSheetParticle {

    protected Vec3 deltaSpeed;
    public SnowTornadoParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ);
        this.deltaSpeed = new Vec3(pXSpeed, pYSpeed, pZSpeed);
        this.quadSize *= 0.75F;
        this.lifetime = 20;
        this.hasPhysics = false;
        this.friction = 1;
    }

    @Override
    public void tick() {
        this.onGround = false;
        super.tick();
        this.xd = (3 * age) * (Math.cos(age) - Math.cos(age + 0.01D));
        this.zd = (3 * age) * (Math.sin(age) - Math.sin(age + 0.01D));
        this.yd = 0.075*2;
        this.yd += deltaSpeed.y;
        this.xd += deltaSpeed.x;
        this.zd += deltaSpeed.z;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprite;
        public Provider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            SnowTornadoParticle particle = new SnowTornadoParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
            particle.pickSprite(sprite);
            return particle;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
