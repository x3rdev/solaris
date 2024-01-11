package com.github.x3r.solaris.mixin;

import com.github.x3r.solaris.client.model.armor.SolarisArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin {

    @Inject(method = "getArmorModelHook", at = @At("RETURN") ,remap = false)
    private void getArmorModelHook(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> _default, CallbackInfoReturnable<Model> cir) {
        if(cir.getReturnValue() instanceof SolarisArmorModel sModel) {
            sModel.setAllVisible(false);
            switch(slot) {
                case HEAD:
                    sModel.head.visible = true;
                    sModel.hat.visible = true;
                    break;
                case CHEST:
                    sModel.body.visible = true;
                    sModel.rightArm.visible = true;
                    sModel.leftArm.visible = true;
                    break;
                case LEGS:
                    sModel.rightLeg.visible = true;
                    sModel.leftLeg.visible = true;
                    break;
                case FEET:
                    sModel.rightBoot.visible = true;
                    sModel.leftBoot.visible = true;
            }
        }
    }
}
