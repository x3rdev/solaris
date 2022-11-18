package com.github.x3rmination.solaris.common.item.SpringWind;

import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.item.SpringWind.CherryBlossomSeeker.CherryBlossomSeekerEntity;
import com.github.x3rmination.solaris.common.scheduler.Executable;
import com.github.x3rmination.solaris.common.scheduler.ServerScheduler;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.ForgeTier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

import java.util.function.Consumer;

public class SpringWindItem extends SwordItem implements IAnimatable, SolarisWeapon {

    public AnimationFactory factory = new AnimationFactory(this);
    public SpringWindItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -3F, pProperties);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) throws NoSuchMethodException {
        if(skill.equals(Skills.FATAL_DRAW)) {
            ServerScheduler.schedule(new Executable(
                    this,
                    this.getClass().getDeclaredMethod("springWindServer", ServerPlayer.class),
                    new Object[]{serverPlayer}, 35));
        }
    }

    public void springWindServer(ServerPlayer serverPlayer) {
        CompoundTag tag = serverPlayer.getMainHandItem().getTagElement("solaris.spring_wind.active");
        tag.putBoolean("active", true);
        tag.putInt("delay", 20*20);
        //Code for spawning seekers
        for(int i = 0; i < 4; i++) {
            spawnSeeker(serverPlayer, 1.7F * i);
        }
    }

    public void spawnSeeker(ServerPlayer player, float offset) {
        CherryBlossomSeekerEntity seekerEntity = new CherryBlossomSeekerEntity(player.level, player, offset);
        seekerEntity.setPos(player.position().add(0, 2, 0));
        player.level.addFreshEntity(seekerEntity);
    }

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

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new SpringWindRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }


}
