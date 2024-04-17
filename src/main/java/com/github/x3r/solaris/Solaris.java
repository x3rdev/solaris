package com.github.x3r.solaris;

import com.github.x3r.solaris.client.ClientSetup;
import com.github.x3r.solaris.client.KeyEvents;
import com.github.x3r.solaris.common.CommonSetup;
import com.github.x3r.solaris.common.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Solaris.MOD_ID)
public class Solaris {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "solaris";

    public Solaris() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::onInterModEnqueueEvent);

        modEventBus.addListener(CommonSetup::setup);
        modEventBus.addListener(CommonSetup::attributeSetup);
        modEventBus.addListener(CommonSetup::spawnPlacementSetup);
        modEventBus.addListener(AnimationRegistry::registerAnimations);
        modEventBus.addListener(WeaponCapabilityRegistry::registerWeaponCapabilities);

        BlockRegistry.BLOCKS.register(modEventBus);
        BlockItemRegistry.BLOCK_ITEMS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        EntityRegistry.ENTITIES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        ItemRegistry.ModItemTab.CREATIVE_MODE_TABS.register(modEventBus);
        MobEffectRegistry.POTIONS.register(modEventBus);
        ParticleRegistry.PARTICLE_TYPES.register(modEventBus);
        SoundRegistry.SOUND_EVENTS.register(modEventBus);
        SurfaceRuleRegistry.SURFACE_RULES.register(modEventBus);

        forgeBus.register(this);
        forgeBus.addListener(SkillsRegistry::buildSkills);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onInterModEnqueueEvent(final InterModEnqueueEvent event) {

    }

}
