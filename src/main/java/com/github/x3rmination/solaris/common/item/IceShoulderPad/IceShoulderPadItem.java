package com.github.x3rmination.solaris.common.item.IceShoulderPad;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IceShoulderPadItem extends Item {
    public IceShoulderPadItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    @SubscribeEvent
    public static void hitEvent(LivingDamageEvent event) {
        if(event.getSource().getEntity() instanceof LivingEntity livingEntity && !CuriosApi.getCuriosHelper().findCurios(livingEntity, ItemRegistry.ICE_SHOULDER_PAD.get()).isEmpty()) {
            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 10, 1));
            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 10, 1));
        }
    }
}
