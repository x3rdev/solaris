package com.github.x3r.solaris.client.particle;

import com.github.x3r.solaris.client.particle.option.BlizzardOption;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class BlizzardParticle extends TextureSheetParticle {

    public BlizzardParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.quadSize *= 0.75F;
        this.lifetime = 200;
        this.hasPhysics = false;
        this.friction = 1;
    }

    @Override
    public void tick() {
        this.onGround = false;
        super.tick();
        double r = 3.5*Math.log(age);
        this.xd = r*(0.065)*(Math.sin(age/2.5) + Math.sin(age/2.5 + 0.01D)) + ((this.random.nextFloat() - 0.5) * 0.1);
        this.zd = r*(0.065)*(Math.cos(age/2.5) + Math.cos(age/2.5 + 0.01D)) + ((this.random.nextFloat() - 0.5) * 0.1);
        this.yd = 0.25*Math.sin(2*age + 34F) + ((this.random.nextFloat() - 0.5) * 0.1);
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        if(age > 10) {
            super.render(pBuffer, pRenderInfo, pPartialTicks);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<BlizzardOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        @Nullable
        @Override
        public Particle createParticle(BlizzardOption pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            BlizzardParticle particle = new BlizzardParticle(pLevel, pX - 0.5, pY, pZ + 1);
            particle.pickSprite(sprites);
            particle.age = pType.getAge();
            return particle;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
