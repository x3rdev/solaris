package com.github.x3rmination.solaris.common.item;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.client.render.item.CustomArmorRenderProperties;
import com.github.x3rmination.solaris.common.item.CentipedeScaleArmor.CentipedeScaleArmorItem;
import com.github.x3rmination.solaris.common.registry.ArmorMaterialRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SolarisArmorItem extends ArmorItem {
    public SolarisArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new CustomArmorRenderProperties());
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        int id = slot == EquipmentSlot.LEGS ? 2 : 1;
        //TODO remove this part
        if(stack.getItem() instanceof CentipedeScaleArmorItem) {
            id = 1;
        }
        return String.format("%s:textures/armor/%s_layer_%d.png", Solaris.MOD_ID, this.material.getName().substring(this.material.getName().indexOf(':') + 1), id);
    }
}
