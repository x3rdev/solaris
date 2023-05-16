package com.github.x3rmination.solaris.common.mob_effect;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FrostbiteMobEffect extends MobEffect {
    public FrostbiteMobEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @SubscribeEvent
    public static void effectApplied(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if(entity.hasEffect(MobEffectRegistry.FROSTBITE.get())) {
            entity.setDeltaMovement(0, event.getEntityLiving().getDeltaMovement().y, 0);
            if(entity.getEffect(MobEffectRegistry.FROSTBITE.get()).getAmplifier() > 3) {
                entity.removeEffect(MobEffectRegistry.FROSTBITE.get());
                entity.hurt(DamageSource.FREEZE, 6);
                for(int i = 0; i < 10; i++) {
                    entity.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.ICE.defaultBlockState()), entity.getX(), entity.getY() + 1, entity.getZ(), entity.getRandom().nextFloat(), entity.getRandom().nextFloat(), entity.getRandom().nextFloat());
                }
            }
        }
    }

    public static void applyEffect(LivingEntity target) {
        MobEffectInstance effect = target.getEffect(MobEffectRegistry.FROSTBITE.get());
        int potency;
        if(effect == null) {
            potency = 0;
        } else {
            potency = effect.getAmplifier() + 1;
        }
        target.addEffect(new MobEffectInstance(MobEffectRegistry.FROSTBITE.get(), 40, potency));
    }
}
