package com.github.x3rmination.solaris.mixin;

import com.github.x3rmination.solaris.client.model.armor.SolarisArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import yesman.epicfight.api.client.model.ClientModel;
import yesman.epicfight.api.client.model.CustomModelBakery;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

//I'm sorry
@Mixin(CustomModelBakery.class)
public abstract class CustomModelBakeryMixin {

    @Inject(method = "bakeBipedCustomArmorModel", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 7), locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    private static void leggingsArmorModel(HumanoidModel<?> model, ArmorItem armorItem, EquipmentSlot slot, boolean debuggingMode, CallbackInfoReturnable<ClientModel> cir, List boxes) {
        if(model instanceof SolarisArmorModel sModel) {
            boxes.remove(boxes.size() - 3);
        }
    }

    @Inject(method = "bakeBipedCustomArmorModel", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 9), locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    private static void bootArmorModel(HumanoidModel<?> model, ArmorItem armorItem, EquipmentSlot slot, boolean debuggingMode, CallbackInfoReturnable<ClientModel> cir, List boxes) {
        try {
            if(model instanceof SolarisArmorModel sModel) {
                boxes.remove(boxes.size() - 1);
                boxes.remove(boxes.size() - 1);
                sModel.leftBoot.copyFrom(sModel.leftLeg);
                sModel.rightBoot.copyFrom(sModel.rightLeg);
                boxes.add(newModelPartition(newSimpleBaker(5), newSimpleBaker(5), sModel.leftBoot));
                boxes.add(newModelPartition(newSimpleBaker(2), newSimpleBaker(2), sModel.rightBoot));
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object newSimpleBaker(int jointId) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> simpleBaker = CustomModelBakery.class.getDeclaredClasses()[2];
        Constructor<?> constructor = simpleBaker.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return constructor.newInstance(jointId);
    }

    private static Object newModelPartition(Object partedBaker, Object childBaker, ModelPart modelRenderer) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> simpleBaker = CustomModelBakery.class.getDeclaredClasses()[0];
        Constructor<?> constructor = simpleBaker.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return constructor.newInstance(partedBaker, childBaker, modelRenderer);
    }

}
