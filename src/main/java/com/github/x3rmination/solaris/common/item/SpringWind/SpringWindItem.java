package com.github.x3rmination.solaris.common.item.SpringWind;

import com.github.x3rmination.solaris.common.entity.attack.CherryBlossomSeeker.CherryBlossomSeekerEntity;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.scheduler.Scheduler;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.ForgeTier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;

import java.util.List;
import java.util.function.Consumer;

public class SpringWindItem extends SwordItem implements IAnimatable, SolarisWeapon {

    public static final String FIRST_MODE = "first_mode";
    public static final String DELAY = "delay";
    public static final String ACTIVE = "active";
    public static final String SEEKER_LIST = "seeker_list";
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public static final int SEEKER_COUNT = 4;
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
    public void serverAttack(ServerPlayer serverPlayer, Skill skill) {
        if (skill.equals(Skills.FATAL_DRAW)) {
            Scheduler.schedule(() -> {
                CompoundTag tag = serverPlayer.getMainHandItem().getTag();
                assert tag != null;
                if(!tag.getBoolean(ACTIVE)) {
                    int[] seekerArray = new int[SEEKER_COUNT];
                    for (int i = 0; i < SEEKER_COUNT; i++) {
                        seekerArray[i] = spawnSeeker(serverPlayer, 1.7F * i);
                    }
                    tag.putIntArray(SEEKER_LIST, seekerArray);
                    setActive(serverPlayer);
                }
            }, 10);
        } else {
            startSeekerMovement(serverPlayer);
            CompoundTag tag = serverPlayer.getMainHandItem().getTag();
            if(tag.getBoolean(ACTIVE) && tag.getBoolean(FIRST_MODE)) {
                setActive(serverPlayer);
            }
        }
    }

    @Override
    public void activateAbility(ServerPlayer serverPlayer) {
        if(serverPlayer.getMainHandItem().getItem() instanceof SpringWindItem) {
            CompoundTag tag = serverPlayer.getMainHandItem().getTag();
            tag.putBoolean(FIRST_MODE, !tag.getBoolean(FIRST_MODE));
        }
    }

    private void setActive(ServerPlayer serverPlayer) {
        CompoundTag tag = serverPlayer.getMainHandItem().getTag();
        tag.putBoolean(ACTIVE, true);
        tag.putInt(DELAY, 20*20);
        for(int id : tag.getIntArray(SEEKER_LIST)) {
            CherryBlossomSeekerEntity seekerEntity = (CherryBlossomSeekerEntity) serverPlayer.level.getEntity(id);
            if(seekerEntity != null) {
                seekerEntity.tickCount = 0;
            }
        }
    }

    public void startSeekerMovement(ServerPlayer serverPlayer) {
        List<Entity> list = getTargets(serverPlayer);
        for (Entity targetEntity : list) {
            Vec3 lookVector = serverPlayer.getLookAngle().multiply(1, 0, 1).normalize();
            Vec3 targetVector = targetEntity.position().subtract(serverPlayer.position()).multiply(1, 0, 1).normalize();
            double lookAngle = Math.atan(lookVector.x/lookVector.z);
            double targetAngle = Math.atan(targetVector.x/targetVector.z);
            if(Math.abs(lookAngle - targetAngle) < Math.PI/6) {
                for (int seeker : serverPlayer.getMainHandItem().getTag().getIntArray(SEEKER_LIST)) {
                    CherryBlossomSeekerEntity seekerEntity = ((CherryBlossomSeekerEntity) serverPlayer.level.getEntity(seeker));
                    if(seekerEntity != null) {
                        seekerEntity.setTarget(targetEntity);
                    }
                }
                return;
            }
        }
    }

    public List<Entity> getTargets(LivingEntity livingEntity) {
        Vec3 ownerPos = livingEntity.position();
        AABB seekingArea = new AABB(ownerPos.x - 10, ownerPos.y - 10, ownerPos.z - 10, ownerPos.x + 10, ownerPos.y + 10, ownerPos.z + 10);
        List<Entity> seekTargets = livingEntity.level.getEntities(livingEntity, seekingArea);
        seekTargets.removeIf(CherryBlossomSeekerEntity.class::isInstance);
        seekTargets.removeIf(entity -> !(entity instanceof LivingEntity));
        seekTargets.removeIf(entity -> !entity.isAlive());
        return seekTargets;
    }

    public int spawnSeeker(ServerPlayer player, float offset) {
        CherryBlossomSeekerEntity seekerEntity = new CherryBlossomSeekerEntity(player, offset);
        seekerEntity.setPos(player.position().add(0, 2, 0));
        player.level.addFreshEntity(seekerEntity);
        return seekerEntity.getId();
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag tag = pStack.getTag();
        if(tag.getInt(DELAY) > 0) {
            tag.putInt(DELAY, tag.getInt(DELAY) - 1);
            if(tag.getInt(DELAY) <= 0) {
                tag.putBoolean(ACTIVE, false);
                tag.putIntArray(SEEKER_LIST, new int[SEEKER_COUNT]);
            }
        }
        if(pEntity instanceof ServerPlayer serverPlayer && !tag.getBoolean(FIRST_MODE)) {
            for (int seeker : pStack.getTag().getIntArray(SEEKER_LIST)) {
                if(serverPlayer.level.getEntity(seeker) instanceof CherryBlossomSeekerEntity seekerEntity) {
                    seekerEntity.moveToSmooth(serverPlayer.getLookAngle().scale(5).add(serverPlayer.position()).add(0, 1, 0), 2);
                }
            }
        }
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

    @Override
    public void verifyTagAfterLoad(CompoundTag pCompoundTag) {
        super.verifyTagAfterLoad(pCompoundTag);
        pCompoundTag.putBoolean(ACTIVE, false);
        pCompoundTag.putInt(DELAY, 0);
        pCompoundTag.putBoolean(FIRST_MODE, true);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getTag();
        if(tag.getBoolean(FIRST_MODE)) {
            pTooltipComponents.add(new TextComponent("MODE 1"));
        } else {
            pTooltipComponents.add(new TextComponent("MODE 2"));
        }
    }
}
