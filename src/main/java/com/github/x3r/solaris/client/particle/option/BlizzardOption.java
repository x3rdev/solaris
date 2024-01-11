package com.github.x3r.solaris.client.particle.option;

import com.github.x3r.solaris.common.registry.ParticleRegistry;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;

public class BlizzardOption implements ParticleOptions {

    public static final Codec<BlizzardOption> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("height").forGetter((option) -> {
            return option.height;
        })).apply(instance, BlizzardOption::new);
    });

    public static final ParticleOptions.Deserializer<BlizzardOption> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public BlizzardOption fromCommand(ParticleType<BlizzardOption> pParticleType, StringReader pReader) throws CommandSyntaxException {
            pReader.expect(' ');
            int i = pReader.readInt();
            return new BlizzardOption(i);
        }

        @Override
        public BlizzardOption fromNetwork(ParticleType<BlizzardOption> pParticleType, FriendlyByteBuf pBuffer) {
            return new BlizzardOption(pBuffer.readInt());
        }
    };
    private final int height;

    public BlizzardOption(int height) {
        this.height = height;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegistry.BLIZZARD.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(height);
    }

    @Override
    public String writeToString() {
        return String.format("%s %d", BuiltInRegistries.PARTICLE_TYPE.getId(getType()), height);
    }

    public int getAge() {
        return height;
    }

}
