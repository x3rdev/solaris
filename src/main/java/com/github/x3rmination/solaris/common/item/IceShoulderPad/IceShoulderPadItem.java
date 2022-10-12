package com.github.x3rmination.solaris.common.item.IceShoulderPad;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.common.capability.CurioItemCapability;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IceShoulderPadItem extends Item {
    public IceShoulderPadItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    @SubscribeEvent
    public static void hitEvent(LivingDamageEvent event) {
        if(event.getSource().getEntity() instanceof LivingEntity livingEntity && !CuriosApi.getCuriosHelper().findCurios(livingEntity, ItemRegistry.ICE_SHOULDER_PAD.get()).isEmpty()) {
            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 10, 1));
            event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * 10, 1));
        }
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return CurioItemCapability.createProvider(new ICurio() {
            @Override
            public ItemStack getStack() {
                return new ItemStack(ItemRegistry.ICE_SHOULDER_PAD.get(), 1);
            }
        });
    }
}
