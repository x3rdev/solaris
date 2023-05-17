package com.github.x3rmination.solaris.client.particle.option;

import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class SnowTornadoOption implements ParticleOptions {

    public static final Codec<SnowTornadoOption> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("age").forGetter((option) -> {
            return option.age;
        })).apply(instance, SnowTornadoOption::new);
    });

    public static final Deserializer<SnowTornadoOption> DESERIALIZER = new Deserializer<>() {
        @Override
        public SnowTornadoOption fromCommand(ParticleType<SnowTornadoOption> pParticleType, StringReader pReader) throws CommandSyntaxException {
            pReader.expect(' ');
            int i = pReader.readInt();
            return new SnowTornadoOption(i);
        }

        @Override
        public SnowTornadoOption fromNetwork(ParticleType<SnowTornadoOption> pParticleType, FriendlyByteBuf pBuffer) {
            return new SnowTornadoOption(pBuffer.readInt());
        }
    };
    private final int age;

    public SnowTornadoOption(int age) {
        this.age = age;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegistry.SNOW_TORNADO.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(age);
    }

    @Override
    public String writeToString() {
        return String.format("%s %d", getType().getRegistryName(), age);
    }

    public int getAge() {
        return age;
    }
}
