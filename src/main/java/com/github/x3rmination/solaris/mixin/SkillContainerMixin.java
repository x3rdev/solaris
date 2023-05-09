package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.common.item.SolarisWeapon;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.Set;

@Mixin(SkillContainer.class)
public abstract class SkillContainerMixin {

    @Inject(method = "sendExecuteRequest", at = @At("HEAD"), remap = false)
    public void clientExecute(LocalPlayerPatch executer, Set<Object> packetStorage, CallbackInfoReturnable<Boolean> cir) throws NoSuchMethodException {
        SkillContainer skillContainer = ((SkillContainer) (Object) this);
        LocalPlayer localPlayer = executer.getOriginal();
        if(localPlayer.getMainHandItem().getItem() instanceof SolarisWeapon solarisWeapon) {
            solarisWeapon.clientAttack(localPlayer, skillContainer.getSkill());
        }
    }
    @Inject(method = "requestExecute", at = @At("HEAD"), remap = false)
    public void serverExecute(ServerPlayerPatch executer, FriendlyByteBuf buf, CallbackInfoReturnable<Boolean> cir) throws NoSuchMethodException {
        SkillContainer skillContainer = ((SkillContainer) (Object) this);
        ServerPlayer serverPlayer = executer.getOriginal();
        if(serverPlayer.getMainHandItem().getItem() instanceof SolarisWeapon solarisWeapon) {
            solarisWeapon.serverAttack(serverPlayer, skillContainer.getSkill());
        }
    }
}
