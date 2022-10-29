package com.github.x3rmination.solaris.common.item.FireKatana;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.registry.MobEffectRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FireKatanaItem extends SwordItem {
    public FireKatanaItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -2F, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setRemainingFireTicks(Math.max(100, pTarget.getRemainingFireTicks()));
        pTarget.hurt(DamageSource.ON_FIRE, 1);
//        pTarget.addEffect(new MobEffectInstance(MobEffectRegistry.FIRE_RING.get(), 5 * 20));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Vec3 pos = event.getEntityLiving().position();
        if(event.getSource().getDirectEntity() instanceof FireKatanaAttackEntity) {
            ParticleHelper particleHelper = new ParticleHelper(event.getSource().getEntity().level, ParticleTypes.FLAME, pos.add(0, 1, 0));
            particleHelper.spawnCircle(2, 16);
            particleHelper.spawnCircle(4, 32);
            particleHelper.spawnCircle(6, 64);
            particleHelper.spawnCircle(8, 128);
            particleHelper.spawnCircle(10, 256);
            AABB hitBox = new AABB(pos.x - 10, pos.y - 1, pos.z - 10, pos.x + 10, pos.y + 1, pos.z + 10);
            for(Entity toHit : entity.level.getEntities(entity, hitBox)) {
                toHit.hurt(DamageSource.ON_FIRE, 2);
                toHit.setRemainingFireTicks(Math.max(toHit.getRemainingFireTicks(), 20));
            }
        }
    }


}
