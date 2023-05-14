package com.github.x3rmination.solaris.common.item.Frostbite;

import com.github.x3rmination.solaris.common.entity.attack.FrostbiteAttack.FrostbiteAttackEntity;
import com.github.x3rmination.solaris.common.item.SolarisParticleWeapon;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.mob_effect.FrostbiteMobEffect;
import com.github.x3rmination.solaris.common.scheduler.Scheduler;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

public class FrostbiteItem extends SwordItem implements SolarisParticleWeapon, SolarisWeapon {

    public FrostbiteItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -2F, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        FrostbiteMobEffect.applyEffect(pTarget);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public Vector3f[] getParticles() {
        return SolarisParticleWeapon.GENERIC_PARTICLE_ARRAY;
    }

    @Override
    public ParticleOptions getParticleType() {
        return ParticleTypes.SNOWFLAKE;
    }

    @Override
    public int getParticleDelay() {
        return 6;
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) {
        if(skill.equals(Skills.FATAL_DRAW)) {
            Scheduler.schedule(() -> {
                FrostbiteAttackEntity frostbiteAttackEntity = new FrostbiteAttackEntity(serverPlayer, serverPlayer.getLookAngle().x, 0, serverPlayer.getLookAngle().z, serverPlayer.level);
                frostbiteAttackEntity.setPos(serverPlayer.position().add(serverPlayer.getLookAngle().multiply(3, 0, 3)));
                frostbiteAttackEntity.setDeltaMovement(serverPlayer.getLookAngle().scale(0.15).multiply(1,0,1));
                serverPlayer.level.addFreshEntity(frostbiteAttackEntity);
            }, 35);
        }
    }

}
