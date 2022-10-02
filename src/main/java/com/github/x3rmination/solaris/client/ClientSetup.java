package com.github.x3rmination.solaris.client;

import com.github.x3rmination.solaris.client.layer.FrostBiteLayer;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ClientSetup {

    public static void setup(final FMLClientSetupEvent event) {

    }

    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        List<EntityType<? extends LivingEntity>> entityTypes = ImmutableList.copyOf(
                ForgeRegistries.ENTITIES.getValues().stream()
                        .filter(DefaultAttributes::hasSupplier)
                        .map(entityType -> (EntityType<? extends LivingEntity>) entityType)
                        .toList());
        entityTypes.forEach((entityType -> {
            if(entityType != EntityType.ENDER_DRAGON) {
                LivingEntityRenderer<LivingEntity, ? extends net.minecraft.client.model.EntityModel<LivingEntity>> renderer = event.getRenderer(entityType);
                if (renderer != null) {
                    renderer.addLayer(new FrostBiteLayer(renderer));
                }
            }
        }));
        for (String skinType : event.getSkins()){
            if(event.getSkin(skinType) != null) {
                event.getSkin(skinType).addLayer(new FrostBiteLayer(event.getSkin(skinType)));
            }
        }
    }
}
