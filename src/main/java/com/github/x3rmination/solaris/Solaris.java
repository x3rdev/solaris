package com.github.x3rmination.solaris;

import com.github.x3rmination.solaris.client.ClientSetup;
import com.github.x3rmination.solaris.client.KeyEvents;
import com.github.x3rmination.solaris.common.CommonSetup;
import com.github.x3rmination.solaris.common.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(Solaris.MOD_ID)
public class Solaris {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "solaris";

    public Solaris() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::onInterModEnqueueEvent);

        modEventBus.addListener(ClientSetup::setup);
        modEventBus.addListener(ClientSetup::addLayers);
        modEventBus.addListener(ClientSetup::registerLayerDefinitions);
        modEventBus.addListener(ClientSetup::registerRenderers);
        modEventBus.addListener(ClientSetup::registerParticleFactories);
        modEventBus.addListener(ClientSetup::registerShaders);
        forgeBus.addListener(KeyEvents::keyPressed);

        modEventBus.addListener(CommonSetup::setup);
        modEventBus.addListener(CommonSetup::registerEFMWeaponCaps);

        BiomeRegistry.BIOMES.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockItemRegistry.BLOCK_ITEMS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        EntityRegistry.ENTITIES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        MobEffectRegistry.POTIONS.register(modEventBus);
        ParticleRegistry.PARTICLE_TYPES.register(modEventBus);
        SoundRegistry.SOUND_EVENTS.register(modEventBus);

        forgeBus.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @SubscribeEvent
    public void onInterModEnqueueEvent(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BODY.getMessageBuilder().build());
    }

}
