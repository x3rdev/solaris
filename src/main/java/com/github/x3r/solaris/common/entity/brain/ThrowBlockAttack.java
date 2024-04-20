package com.github.x3r.solaris.common.entity.brain;

import com.github.x3r.solaris.common.datagen.SolarisBlockTagsProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.util.BrainUtils;

public class ThrowBlockAttack <E extends LivingEntity & RangedAttackMob> extends AnimatableRangedAttack<E> {

    public ThrowBlockAttack(int delayTicks) {
        super(delayTicks);
    }

    @Override
    protected void start(E entity) {
        BehaviorUtils.lookAtEntity(entity, this.target);

    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
        Vec3 pickupOffset = entity.position().add(new Vec3(-1, -1, -1).yRot((float) Math.toRadians(180 - entity.getYHeadRot())));
        BlockPos pos1 = new BlockPos((int) pickupOffset.x, (int) pickupOffset.y, (int) pickupOffset.z);
        boolean b = false;
        if(level.getBlockState(pos1).is(SolarisBlockTagsProvider.SNOW_TROLL_THROWABLE)) {
            b = true;
        }
        return b && super.checkExtraStartConditions(level, entity);
    }

    @Override
    protected void doDelayedAction(E entity) {
        if (this.target == null)
            return;

        if (!BrainUtils.canSee(entity, this.target) || entity.distanceToSqr(this.target) > this.attackRadius)
            return;

        entity.performRangedAttack(this.target, 3);
        BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, this.attackIntervalSupplier.apply(entity));
    }
}
