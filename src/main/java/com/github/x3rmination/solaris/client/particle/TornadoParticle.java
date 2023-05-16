package com.github.x3rmination.solaris.client.particle;

import com.github.x3rmination.solaris.client.particle.option.AirTornadoOption;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class TornadoParticle extends TextureSheetParticle {

    protected Vec3 deltaSpeed;
    public TornadoParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ);
        this.deltaSpeed = new Vec3(pXSpeed, pYSpeed, pZSpeed);
        this.quadSize *= 0.75F;
        this.lifetime = 60;
        this.hasPhysics = false;
        this.friction = 1;
    }

    @Override
    public void tick() {
        this.onGround = false;
        super.tick();
        this.xd = (3 * age) * (Math.cos(age) - Math.cos(age + 0.01D));
        this.zd = (3 * age) * (Math.sin(age) - Math.sin(age + 0.01D));
        this.yd = 0.075;
        this.yd += deltaSpeed.y;
        this.xd += deltaSpeed.x;
        this.zd += deltaSpeed.z;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<AirTornadoOption> {

        private final SpriteSet sprite;
        public Provider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        @Nullable
        @Override
        public Particle createParticle(AirTornadoOption pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            TornadoParticle particle = new TornadoParticle(pLevel, pX, pY + 0.075 * pType.getAge(), pZ, pXSpeed, pYSpeed, pZSpeed);
            particle.pickSprite(sprite);
            particle.age = pType.getAge();
            return particle;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
