package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.client.animation.ClientAnimator;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.lang.reflect.Field;
import java.util.Random;

@Mixin(AttackAnimation.class)
public abstract class AttackAnimationMixin {

    @Shadow(remap = false) public abstract Collider getCollider(LivingEntityPatch<?> entitypatch, float elapsedTime);

    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    private void tickMixin(LivingEntityPatch<?> entitypatch, CallbackInfo ci) {
        AnimationPlayer animPlayer = entitypatch.getAnimator().getPlayerFor((DynamicAnimation) (Object) this);
        float elapsedTime = animPlayer.getElapsedTime();
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.getMainHandItem().getItem() == ItemRegistry.FLAMING_FLAMBERGE.get()) {
            Collider collider = getCollider(entitypatch, elapsedTime);
            try {
                Field field = Collider.class.getDeclaredField("worldCenter");
                field.setAccessible(true);
                Vec3 hitBox = (Vec3) field.get(collider);
                if (hitBox != null) {
                    Random random = new Random();
                    Minecraft.getInstance().level.addParticle(ParticleTypes.FLAME, -hitBox.x, hitBox.y, -hitBox.z, random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
