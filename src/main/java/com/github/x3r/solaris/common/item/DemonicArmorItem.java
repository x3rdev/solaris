package com.github.x3r.solaris.common.item;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.client.model.armor.DemonicArmorModel;
import com.github.x3r.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class DemonicArmorItem extends ArmorItem {

    public DemonicArmorItem(Type pType, Properties pProperties) {
        super(ArmorMaterialRegistry.DEMONIC, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return new ResourceLocation(Solaris.MOD_ID, "textures/armor/demonic_layer_" + (slot == EquipmentSlot.LEGS ? 2 : 1) + ".png").toString();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                ModelPart root = Minecraft.getInstance().getEntityModels()
                        .bakeLayer(equipmentSlot == EquipmentSlot.LEGS ? DemonicArmorModel.DEMONIC_ARMOR_INNER : DemonicArmorModel.DEMONIC_ARMOR_OUTER);
                return new DemonicArmorModel(root);
            }
        });
    }
}
