package com.github.x3rmination.solaris.common.scheduler;

import com.github.x3rmination.solaris.Solaris;
import com.google.common.util.concurrent.AbstractScheduledService;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Solaris.MOD_ID)
public final class ClientScheduler {
    public static final List<Executable> executableList = new ArrayList<>();

    private ClientScheduler() {

    }

    public static void schedule(Executable executable) {
        if(executable.getMethod().canAccess(executable.getObject())) {
            executableList.add(executable);
        }
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if(!executableList.isEmpty()) {
            List<Executable> removalList = new ArrayList<>();
            for(Executable executable : executableList) {
                if(executable.getDelay() > 0) {
                    executable.decreaseDelay();
                } else {
                    try {
                        executable.getMethod().invoke(executable.getObject(), executable.getArgs());
                        removalList.add(executable);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            executableList.removeAll(removalList);
        }
    }
}
