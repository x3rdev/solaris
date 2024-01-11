package com.github.x3r.solaris.client;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.layer.BodyPartLayer;
import com.github.x3r.solaris.client.layer.FrostBiteLayer;
import com.github.x3r.solaris.client.model.armor.CentipedeScaleArmorModel;
import com.github.x3r.solaris.client.model.armor.MonkArmorModel;
import com.github.x3r.solaris.client.model.armor.SolarArmorModel;
import com.github.x3r.solaris.client.model.armor.WhitestoneArmorModel;
import com.github.x3r.solaris.client.particle.*;
import com.github.x3r.solaris.common.registry.ParticleRegistry;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ClientSetup {

    @Nullable
    private static ShaderInstance rendertypeAbyssalEdge;

    public static void setup(final FMLClientSetupEvent event) {

    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        List<EntityType<? extends LivingEntity>> entityTypes = ImmutableList.copyOf(
                ForgeRegistries.ENTITY_TYPES
                        .getValues().stream()
                        .filter(DefaultAttributes::hasSupplier)
                        .map(entityType -> (EntityType<? extends LivingEntity>) entityType)
                        .toList());
        entityTypes.forEach((entityType -> {
            try {
                if (entityType != EntityType.ENDER_DRAGON) {
                    LivingEntityRenderer<LivingEntity, ? extends EntityModel<LivingEntity>> renderer = event.getRenderer(entityType);
                    if (renderer != null) {
                        renderer.addLayer(new FrostBiteLayer(renderer));
                    }
                }
            } catch (Exception e) {
                Solaris.LOGGER.warn(ForgeRegistries.ENTITY_TYPES.getKey(entityType) + " has custom renderer, FrostBiteLayer could not be added");
            }
        }));
        for (String skinType : event.getSkins()){
            EntityRenderer<? extends Player> renderer = event.getSkin(skinType);
            if(renderer instanceof LivingEntityRenderer livingRenderer) {
                livingRenderer.addLayer(new FrostBiteLayer(livingRenderer));
                livingRenderer.addLayer(new BodyPartLayer(livingRenderer));
            }
        }
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        //Vanilla is 0.5F inner, 1.0F outer, some models may vary
        event.registerLayerDefinition(SolarArmorModel.LAYER_LOCATION, SolarArmorModel::createBodyLayer);
        event.registerLayerDefinition(CentipedeScaleArmorModel.LAYER_LOCATION, CentipedeScaleArmorModel::createBodyLayer);
        event.registerLayerDefinition(MonkArmorModel.LAYER_LOCATION, MonkArmorModel::createBodyLayer);
        event.registerLayerDefinition(WhitestoneArmorModel.LAYER_LOCATION, WhitestoneArmorModel::createBodyLayer);
    }

    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {

    }

    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.ANIMATED_SPARKS.get(), AnimatedSparksParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHERRY_BLOSSOM.get(), CherryBlossomParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.FLAME_0.get(), Flame0Particle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.SNOW_TORNADO.get(), SnowTornadoParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.AIR_TORNADO.get(), AirTornadoParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.BLIZZARD.get(), BlizzardParticle.Provider::new);
    }

    public static ShaderInstance getAbyssalBladeShader() {
        return Objects.requireNonNull(rendertypeAbyssalEdge, "Attempted to call getAbyssalBladeShader before shaders have finished loading.");
    }
}
