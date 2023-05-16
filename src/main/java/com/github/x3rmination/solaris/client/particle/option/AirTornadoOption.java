package com.github.x3rmination.solaris.client.particle.option;

import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class AirTornadoOption implements ParticleOptions {

    public static final Codec<AirTornadoOption> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("age").forGetter((option) -> {
            return option.age;
        })).apply(instance, AirTornadoOption::new);
    });

    public static final ParticleOptions.Deserializer<AirTornadoOption> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public AirTornadoOption fromCommand(ParticleType<AirTornadoOption> pParticleType, StringReader pReader) throws CommandSyntaxException {
            pReader.expect(' ');
            int i = pReader.readInt();
            return new AirTornadoOption(i);
        }

        @Override
        public AirTornadoOption fromNetwork(ParticleType<AirTornadoOption> pParticleType, FriendlyByteBuf pBuffer) {
            return new AirTornadoOption(pBuffer.readInt());
        }
    };
    private final int age;

    public AirTornadoOption(int age) {
        this.age = age;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegistry.AIR_TORNADO.get();
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
