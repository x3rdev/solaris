package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.common.helper.ParticleHelper;
import com.github.x3rmination.solaris.common.item.AbyssalEdge.AbyssalEdgeAttackEntity;
import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaAttackEntity;
import com.github.x3rmination.solaris.common.item.Frostbite.FrostbiteAttackEntity;
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
        if(skillContainer.getSkill().equals(Skills.FATAL_DRAW)) {
            if (localPlayer.getMainHandItem().getItem() == ItemRegistry.FROSTBITE.get()) {
                ClientScheduler.schedule(new Executable(
                        this,
                        this.getClass().getDeclaredMethod("frostbiteClient", LocalPlayer.class),
                        new Object[]{localPlayer}, 35));
            }
            if (localPlayer.getMainHandItem().getItem() == ItemRegistry.FIRE_KATANA.get()) {
                ClientScheduler.schedule(new Executable(
                        this,
                        this.getClass().getDeclaredMethod("fireKatanaClient", LocalPlayer.class),
                        new Object[]{localPlayer}, 35));
            }
        }
        if(skillContainer.getSkill().equals(Skills.GIANT_WHIRLWIND)) {
            if (localPlayer.getMainHandItem().getItem() == ItemRegistry.FLAMING_FLAMBERGE.get()) {
                ClientScheduler.schedule(new Executable(
                        this,
                        this.getClass().getDeclaredMethod("flamingFlambergeClient", LocalPlayer.class),
                        new Object[]{localPlayer}, 35));
            }
        }
    }

    @Inject(method = "requestExecute", at = @At("HEAD"), remap = false)
    public void serverExecute(ServerPlayerPatch executer, FriendlyByteBuf buf, CallbackInfoReturnable<Boolean> cir) throws NoSuchMethodException {
        SkillContainer skillContainer = ((SkillContainer) (Object) this);
        ServerPlayer serverPlayer = executer.getOriginal();
        if(skillContainer.getSkill() instanceof FatalDrawSkill) {
            if (serverPlayer.getMainHandItem().getItem() == ItemRegistry.FIRE_KATANA.get()) {
                ServerScheduler.schedule(new Executable(
                        this,
                        this.getClass().getDeclaredMethod("fireKatanaServer", ServerPlayer.class),
                        new Object[]{serverPlayer}, 35));
            }
            if (serverPlayer.getMainHandItem().getItem() == ItemRegistry.FROSTBITE.get()) {
                ServerScheduler.schedule(new Executable(
                        this,
                        this.getClass().getDeclaredMethod("frostbiteServer", ServerPlayer.class),
                        new Object[]{serverPlayer}, 35));
            }
            if (serverPlayer.getMainHandItem().getItem() == ItemRegistry.ABYSSAL_EDGE_BLADE.get()) {

                ServerScheduler.schedule(new Executable(
                        this,
                        this.getClass().getDeclaredMethod("abyssalEdgeServer", ServerPlayer.class),
                        new Object[]{serverPlayer}, 35));
            }
        }
    }

    public void fireKatanaClient(LocalPlayer localPlayer) {
        ParticleHelper particleHelper = new ParticleHelper(localPlayer.level, ParticleTypes.FLAME, localPlayer.position().add(0, 1, 0));
        particleHelper.spawnArc(2, 10, Mth.wrapDegrees(localPlayer.getXRot()), localPlayer.getYRot(), localPlayer.getLookAngle());
    }

    public void fireKatanaServer(ServerPlayer serverPlayer) {
        FireKatanaAttackEntity fireKatanaAttack = new FireKatanaAttackEntity(serverPlayer, serverPlayer.getLookAngle().x, serverPlayer.getLookAngle().y, serverPlayer.getLookAngle().z, serverPlayer.level);
        fireKatanaAttack.setPos(serverPlayer.position().add(serverPlayer.getLookAngle().multiply(3, 0, 3)));
        serverPlayer.level.addFreshEntity(fireKatanaAttack);
    }

    public void frostbiteClient(LocalPlayer localPlayer) {
        ParticleHelper particleHelper = new ParticleHelper(localPlayer.level, ParticleTypes.SNOWFLAKE, localPlayer.position().add(0, 2, 0));
        float yMod = 0;
        while(yMod > -2) {
            particleHelper.spawnCircle(2 + yMod, 16, localPlayer.getLookAngle().multiply(1, 0, 1));
            particleHelper.setPos(particleHelper.getPos().add(0, -0.5, 0));
            yMod-=0.5;
        }
    }

    public void frostbiteServer(ServerPlayer serverPlayer) {
        FrostbiteAttackEntity frostbiteAttackEntity = new FrostbiteAttackEntity(serverPlayer, serverPlayer.getLookAngle().x, 0, serverPlayer.getLookAngle().z, serverPlayer.level);
        frostbiteAttackEntity.setPos(serverPlayer.position().add(serverPlayer.getLookAngle().multiply(3, 0, 3)));
        serverPlayer.level.addFreshEntity(frostbiteAttackEntity);
    }

    public void flamingFlambergeClient(LocalPlayer localPlayer) {
        ParticleHelper particleHelper = new ParticleHelper(localPlayer.level, ParticleTypes.FLAME, localPlayer.position().add(0, 1, 0));
        for(int i = 0; i < 10; i++) {
            particleHelper.spawnCircle(2 + (i * 0.1), (int) (32 + (Math.random() * 4)));
        }
    }

    public void abyssalEdgeServer(ServerPlayer serverPlayer) {
        AbyssalEdgeAttackEntity abyssalEdgeAttackEntity = new AbyssalEdgeAttackEntity(serverPlayer.level);
        abyssalEdgeAttackEntity.setYRot(-serverPlayer.getYHeadRot());
        abyssalEdgeAttackEntity.setPos(serverPlayer.position().add(serverPlayer.getLookAngle().multiply(3, 0, 3)));
        serverPlayer.level.addFreshEntity(abyssalEdgeAttackEntity);
    }
}
