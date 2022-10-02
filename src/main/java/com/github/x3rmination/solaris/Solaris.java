package com.github.x3rmination.solaris;

import com.github.x3rmination.solaris.client.ClientSetup;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Solaris.MOD_ID)
public class Solaris {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "solaris";

    public Solaris() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::setup);
        modEventBus.addListener(ClientSetup::setup);
        modEventBus.addListener(ClientSetup::addLayers);

        ItemRegistry.ITEMS.register(modEventBus);
        MobEffectRegistry.POTIONS.register(modEventBus);

        forgeBus.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

}
