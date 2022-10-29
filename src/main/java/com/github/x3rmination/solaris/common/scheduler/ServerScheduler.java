package com.github.x3rmination.solaris.common.scheduler;


import com.github.x3rmination.solaris.Solaris;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Solaris.MOD_ID)
public final class ServerScheduler {

    public static final List<Executable> executableList = new ArrayList<>();

    private ServerScheduler() {

    }

    public static void schedule(Executable executable) {
        if(executable.getMethod().canAccess(executable.getObject())) {
            executableList.add(executable);
        }
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if(!executableList.isEmpty()) {
            List<Executable> removalList = new ArrayList<>();
            for(Executable executable : executableList) {
                if(executable.getDelay() > 0) {
                    executable.decreaseDelay();
                } else {
                    try {
                        executable.getMethod().invoke(executable.getObject(), executable.getArgs());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    removalList.add(executable);
                }
            }
            executableList.removeAll(removalList);
        }
    }
}
