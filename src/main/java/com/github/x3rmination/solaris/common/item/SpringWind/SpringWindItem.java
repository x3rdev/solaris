package com.github.x3rmination.solaris.common.item.SpringWind;

import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.item.SpringWind.CherryBlossomSeeker.CherryBlossomSeekerEntity;
import com.github.x3rmination.solaris.common.registry.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

public class SpringWindItem extends SwordItem implements SolarisWeapon {

    public SpringWindItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -3F, pProperties);
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) {
        if(skill.equals(Skills.FATAL_DRAW)) {
            CompoundTag tag = serverPlayer.getMainHandItem().getTagElement("solaris.spring_wind.active");
            tag.putBoolean("active", true);
            tag.putInt("delay", 20*10);
            //Code for spawning seekers
            spawnSeeker(serverPlayer);
        }
    }

    public void spawnSeeker(ServerPlayer player) {
        CherryBlossomSeekerEntity seekerEntity = new CherryBlossomSeekerEntity(player.level, player);
        seekerEntity.setPos(player.position().add(0, 2, 0));
        player.level.addFreshEntity(seekerEntity);
    }

    //No intellij I will not define a constant
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!activeTagPresent(pStack)) {
            initializeActiveTag(pStack);
        }
        CompoundTag tag = pStack.getTagElement("solaris.spring_wind.active");
        if(tag.getBoolean("active")) {
            tag.putInt("delay", tag.getInt("delay") - 1);
            if(tag.getInt("delay") <= 0) {
                tag.putBoolean("active", false);
            }
        }
    }

    public void initializeActiveTag(ItemStack stack) {
        CompoundTag activeTag = stack.getOrCreateTagElement("solaris.spring_wind.active");
        if(activeTag.isEmpty()) {
            activeTag.putBoolean("active", false);
            activeTag.putInt("delay", 0);
        }
    }

    public boolean activeTagPresent(ItemStack stack) {
        CompoundTag tag = stack.getTagElement("solaris.spring_wind.active");
        return tag != null && tag.get("active") != null && tag.get("delay") != null;
    }
}
