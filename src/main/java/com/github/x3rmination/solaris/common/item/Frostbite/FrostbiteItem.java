package com.github.x3rmination.solaris.common.item.Frostbite;

import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class FrostbiteItem extends SwordItem {

    public FrostbiteItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -2F, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        MobEffectInstance effect = pTarget.getEffect(MobEffectRegistry.FROSTBITE.get());
        int potency;
        if(effect == null) {
            potency = 0;
        } else {
            potency = effect.getAmplifier() + 1;
        }
        if(potency > 3) {
            pTarget.removeEffect(MobEffectRegistry.FROSTBITE.get());
            pTarget.hurt(DamageSource.FREEZE, 6);
        } else {
            pTarget.addEffect(new MobEffectInstance(MobEffectRegistry.FROSTBITE.get(), 40, potency), pAttacker);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
