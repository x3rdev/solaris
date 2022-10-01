package com.github.x3rmination.solaris.common.item.FlamingFlamberge;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

import java.lang.reflect.Field;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID)
public class FlamingFlambergeParticles {

    @SubscribeEvent
    public static void playerTickEvent(RenderPlayerEvent.Pre event) {
        ItemStack itemStack = event.getPlayer().getMainHandItem();
        if(itemStack.getItem() instanceof FlamingFlambergeItem) {
            Collider collider = EpicFightCapabilities.getItemStackCapability(itemStack).getWeaponCollider();
            try {
                Field field = Collider.class.getDeclaredField("worldCenter");
                field.setAccessible(true);
                Vec3 hitBox = (Vec3) field.get(collider);
                if(hitBox != null) {
                    Random random = new Random();
                    event.getPlayer().level.addParticle(ParticleTypes.FLAME, -hitBox.x, hitBox.y, -hitBox.z, random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F), random.nextFloat(-0.1F, 0.1F));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
