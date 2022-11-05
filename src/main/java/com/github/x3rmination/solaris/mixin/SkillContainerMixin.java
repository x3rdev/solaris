package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.item.AbyssalEdge.AbyssalEdgeAttackEntity;
import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaAttackEntity;
import com.github.x3rmination.solaris.common.item.Frostbite.FrostbiteAttackEntity;
import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.github.x3rmination.solaris.common.scheduler.ClientScheduler;
import com.github.x3rmination.solaris.common.scheduler.Executable;
import com.github.x3rmination.solaris.common.scheduler.ServerScheduler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.FatalDrawSkill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.Set;

@Mixin(SkillContainer.class)
public abstract class SkillContainerMixin {

    @Inject(method = "sendExecuteRequest", at = @At("HEAD"), remap = false)
    public void clientExecute(LocalPlayerPatch executer, Set<Object> packetStorage, CallbackInfoReturnable<Boolean> cir) throws NoSuchMethodException {
        SkillContainer skillContainer = ((SkillContainer) (Object) this);
        LocalPlayer localPlayer = executer.getOriginal();
        if(localPlayer.getMainHandItem().getItem() instanceof SolarisWeapon) {
            if (skillContainer.getSkill().equals(Skills.FATAL_DRAW)) {
                ((SolarisWeapon) localPlayer.getMainHandItem().getItem()).clientAttack(localPlayer);
            }
            if (skillContainer.getSkill().equals(Skills.GIANT_WHIRLWIND)) {
                ((SolarisWeapon) localPlayer.getMainHandItem().getItem()).clientAttack(localPlayer);
            }
        }
    }
    @Inject(method = "requestExecute", at = @At("HEAD"), remap = false)
    public void serverExecute(ServerPlayerPatch executer, FriendlyByteBuf buf, CallbackInfoReturnable<Boolean> cir) throws NoSuchMethodException {
        SkillContainer skillContainer = ((SkillContainer) (Object) this);
        ServerPlayer serverPlayer = executer.getOriginal();
        if(serverPlayer.getMainHandItem().getItem() instanceof SolarisWeapon) {
            if (skillContainer.getSkill().equals(Skills.FATAL_DRAW)) {
                ((SolarisWeapon) serverPlayer.getMainHandItem().getItem()).serverAttack(serverPlayer);
            }
        }
    }
}
