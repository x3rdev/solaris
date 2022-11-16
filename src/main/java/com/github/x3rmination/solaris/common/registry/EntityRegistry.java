package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.AbyssalEdge.AbyssalEdgeAttackEntity;
import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaAttackEntity;
import com.github.x3rmination.solaris.common.item.Frostbite.FrostbiteAttackEntity;
import com.github.x3rmination.solaris.common.item.SpringWind.CherryBlossomSeeker.CherryBlossomSeekerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Solaris.MOD_ID);

    public static final RegistryObject<EntityType<FireKatanaAttackEntity>> FIRE_KATANA_ATTACK = ENTITIES.register("fire_katana_attack",
            () -> EntityType.Builder.<FireKatanaAttackEntity>of(FireKatanaAttackEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Solaris.MOD_ID, "fire_katana_attack").toString()));

    public static final RegistryObject<EntityType<FrostbiteAttackEntity>> FROSTBITE_ATTACK = ENTITIES.register("frostbite_attack",
            () -> EntityType.Builder.<FrostbiteAttackEntity>of(FrostbiteAttackEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Solaris.MOD_ID, "frostbite_attack").toString()));

    public static final RegistryObject<EntityType<AbyssalEdgeAttackEntity>> ABYSSAL_EDGE_ATTACK = ENTITIES.register("abyssal_edge_attack",
            () -> EntityType.Builder.<AbyssalEdgeAttackEntity>of(AbyssalEdgeAttackEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Solaris.MOD_ID, "abyssal_edge_attack").toString()));

    public static final RegistryObject<EntityType<CherryBlossomSeekerEntity>> CHERRY_BLOSSOM_SEEKER = ENTITIES.register("cherry_blossom_seeker",
            () -> EntityType.Builder.<CherryBlossomSeekerEntity>of(CherryBlossomSeekerEntity::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F)
                    .clientTrackingRange(4)
                    .updateInterval(2)
                    .build(new ResourceLocation(Solaris.MOD_ID, "cherry_blossom_seeker").toString()));
}
