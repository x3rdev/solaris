package com.github.x3r.solaris.client;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.model.armor.DemonicArmorModel;
import com.github.x3r.solaris.client.particle.*;
import com.github.x3r.solaris.client.renderer.block.UrborosPolypRenderer;
import com.github.x3r.solaris.client.renderer.block.UrborosStrobilaRenderer;
import com.github.x3r.solaris.client.renderer.entity.*;
import com.github.x3r.solaris.common.entity.SnowTrollEntity;
import com.github.x3r.solaris.common.entity.ThrownBlockEntity;
import com.github.x3r.solaris.common.registry.BlockEntityRegistry;
import com.github.x3r.solaris.common.registry.EntityRegistry;
import com.github.x3r.solaris.common.registry.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void setup(final FMLClientSetupEvent event) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(KeyEvents::keyPressed);

        DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(Solaris.MOD_ID, "solaris"), new DimensionEffects.SolarisEffects());

    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {

    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DemonicArmorModel.DEMONIC_ARMOR_INNER, () -> DemonicArmorModel.createLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION));
        event.registerLayerDefinition(DemonicArmorModel.DEMONIC_ARMOR_OUTER, () -> DemonicArmorModel.createLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION));
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.URBOROS_POLYP.get(), pContext -> new UrborosPolypRenderer());
        event.registerBlockEntityRenderer(BlockEntityRegistry.URBOROS_STROBILA.get(), pContext -> new UrborosStrobilaRenderer());

        event.registerEntityRenderer(EntityRegistry.SCORCHED_BUG.get(), ScorchedBugRenderer::new);
        event.registerEntityRenderer(EntityRegistry.ELEMENTAL.get(), ElementalRenderer::new);
        event.registerEntityRenderer(EntityRegistry.SNOW_TROLL.get(), SnowTrollRenderer::new);
        event.registerEntityRenderer(EntityRegistry.THROWN_BLOCK.get(), ThrownBlockRenderer::new);
        event.registerEntityRenderer(EntityRegistry.URBOROS.get(), UrborosRenderer::new);
        event.registerEntityRenderer(EntityRegistry.URBOROS_EPHYRA.get(), UrborosEphyraRenderer::new);
    }


    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.ANIMATED_SPARKS.get(), AnimatedSparksParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHERRY_BLOSSOM.get(), CherryBlossomParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.FLAME_0.get(), Flame0Particle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.SNOW_TORNADO.get(), SnowTornadoParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.AIR_TORNADO.get(), AirTornadoParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.BLIZZARD.get(), BlizzardParticle.Provider::new);
    }

}
