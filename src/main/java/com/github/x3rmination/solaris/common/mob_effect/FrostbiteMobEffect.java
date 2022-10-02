package com.github.x3rmination.solaris.common.mob_effect;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FrostbiteMobEffect extends MobEffect {
    public FrostbiteMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @SubscribeEvent
    public static void effectApplied(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving().hasEffect(MobEffectRegistry.FROSTBITE.get())) {
            event.getEntityLiving().setDeltaMovement(0, event.getEntityLiving().getDeltaMovement().y, 0);
        }
    }
}
