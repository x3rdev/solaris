package com.github.x3rmination.solaris.common.item.IceHalberd;

import com.github.x3rmination.solaris.common.entity.attack.BlizzardAttack.BlizzardAttackEntity;
import com.github.x3rmination.solaris.common.item.SolarisParticleWeapon;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import com.mojang.math.Vector3f;
import net.minecraft.client.ParticleStatus;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

public class IceHalberdItem extends SwordItem implements SolarisParticleWeapon, SolarisWeapon {
    public IceHalberdItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 9, -3F, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        FrostbiteMobEffect.applyEffect(pTarget);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) throws NoSuchMethodException {
        if(skill.equals(Skills.SLAUGHTER_STANCE)) {
            BlizzardAttackEntity blizzardAttackEntity = new BlizzardAttackEntity(serverPlayer, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), serverPlayer.level);
            serverPlayer.level.addFreshEntity(blizzardAttackEntity);
        }
    }

    @Override
    public Vector3f[] getParticles() {
        return new Vector3f[]{new Vector3f(0, 0, 1.5F)};
    }

    @Override
    public ParticleOptions getParticleType() {
        return ParticleTypes.SNOWFLAKE;
    }

    @Override
    public int getParticleDelay() {
        return 6;
    }
}
