package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.mob_effect.BaseMobEffect;
import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffectRegistry {

    public static final DeferredRegister<MobEffect> POTIONS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Solaris.MOD_ID);
    public static final RegistryObject<MobEffect> FROSTBITE = POTIONS.register("frostbite",
            () -> new FrostbiteMobEffect(MobEffectCategory.HARMFUL, 0x03fce8));
    public static final RegistryObject<MobEffect> FIRE_RING = POTIONS.register("fire_ring",
            () -> new BaseMobEffect(MobEffectCategory.HARMFUL, 0xff5500));
}
