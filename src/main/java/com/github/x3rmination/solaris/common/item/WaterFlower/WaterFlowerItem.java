package com.github.x3rmination.solaris.common.item.WaterFlower;

import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaAttackEntity;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.scheduler.Executable;
import com.github.x3rmination.solaris.common.scheduler.ServerScheduler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeTier;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

public class WaterFlowerItem extends SwordItem implements SolarisWeapon {

    public WaterFlowerItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -3F, pProperties);
    }

    @Override
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) throws NoSuchMethodException {
        if(skill.equals(Skills.FATAL_DRAW)) {
            ServerScheduler.schedule(new Executable(
                    this,
                    this.getClass().getDeclaredMethod("waterFlowerServer", ServerPlayer.class),
                    new Object[]{serverPlayer}, 35));
        }
    }

    public void waterFlowerServer(ServerPlayer serverPlayer) {
        WaterFlowerAttackEntity waterFlowerAttack = new WaterFlowerAttackEntity(serverPlayer);
        waterFlowerAttack.shootFromRotation(serverPlayer, serverPlayer.getXRot(), serverPlayer.getYHeadRot() , 0.0F, 1.5F, 0);
        waterFlowerAttack.move(MoverType.SELF, new Vec3(0, 1, 0));
        serverPlayer.level.addFreshEntity(waterFlowerAttack);
    }
}
