package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.particle.option.AirTornadoOption;
import com.github.x3r.solaris.client.particle.option.BlizzardOption;
import com.github.x3r.solaris.client.particle.option.SnowTornadoOption;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Solaris.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ANIMATED_SPARKS = PARTICLE_TYPES.register("animated_sparks",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHERRY_BLOSSOM = PARTICLE_TYPES.register("cherry_blossom",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FLAME_0 = PARTICLE_TYPES.register("flame_0",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType> SNOW_TORNADO = PARTICLE_TYPES.register("snow_tornado",
            () -> new ParticleType<>(false, SnowTornadoOption.DESERIALIZER) {
                @Override
                public Codec codec() {
                    return SnowTornadoOption.CODEC;
                }
            });

    public static final RegistryObject<ParticleType> AIR_TORNADO = PARTICLE_TYPES.register("air_tornado",
            () -> new ParticleType<>(false, AirTornadoOption.DESERIALIZER) {
                @Override
                public Codec codec() {
                    return AirTornadoOption.CODEC;
                }
            });

    public static final RegistryObject<ParticleType> BLIZZARD = PARTICLE_TYPES.register("blizzard",
            () -> new ParticleType<>(false, BlizzardOption.DESERIALIZER) {
                @Override
                public Codec codec() {
                    return BlizzardOption.CODEC;
                }
            });

}
