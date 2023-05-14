package com.github.x3rmination.solaris.common.item.AbyssalEdge;

import com.github.x3rmination.solaris.common.entity.attack.AbyssalEdgeAttack.AbyssalEdgeAttackEntity;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.scheduler.Scheduler;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.ForgeTier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

import java.util.function.Consumer;

public class AbyssalEdgeBladeItem extends SwordItem implements IAnimatable, SolarisWeapon {
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public AbyssalEdgeBladeItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -2F, pProperties);
    }

    @Override
    public void registerControllers(AnimationData data) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new AbyssalEdgeBladeRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) {
        if(skill.equals(Skills.FATAL_DRAW)) {
            Scheduler.schedule(() -> {
                AbyssalEdgeAttackEntity abyssalEdgeAttackEntity = new AbyssalEdgeAttackEntity(serverPlayer.level);
                abyssalEdgeAttackEntity.setYRot(-serverPlayer.getYHeadRot());
                abyssalEdgeAttackEntity.setPos(serverPlayer.position().add(serverPlayer.getLookAngle().multiply(6, 0, 6)));
                serverPlayer.level.addFreshEntity(abyssalEdgeAttackEntity);
            }, 35);
        }
    }

}
