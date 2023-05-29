package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.entity.attack.AbyssalEdgeAttack.AbyssalEdgeAttackEntity;
import com.github.x3rmination.solaris.common.entity.attack.BlizzardAttack.BlizzardAttackEntity;
import com.github.x3rmination.solaris.common.entity.attack.CloudSplitterAttack.CloudSplitterAttackEntity;
import com.github.x3rmination.solaris.common.entity.attack.FireKatanaAttack.FireKatanaAttackEntity;
import com.github.x3rmination.solaris.common.entity.attack.FrostbiteAttack.FrostbiteAttackEntity;
import com.github.x3rmination.solaris.common.entity.attack.CherryBlossomSeeker.CherryBlossomSeekerEntity;
import com.github.x3rmination.solaris.common.entity.attack.WaterFlowerAttack.WaterFlowerAttackEntity;
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
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build(new ResourceLocation(Solaris.MOD_ID, "fire_katana_attack").toString()));

    public static final RegistryObject<EntityType<FrostbiteAttackEntity>> FROSTBITE_ATTACK = ENTITIES.register("frostbite_attack",
            () -> EntityType.Builder.<FrostbiteAttackEntity>of(FrostbiteAttackEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build(new ResourceLocation(Solaris.MOD_ID, "frostbite_attack").toString()));

    public static final RegistryObject<EntityType<AbyssalEdgeAttackEntity>> ABYSSAL_EDGE_ATTACK = ENTITIES.register("abyssal_edge_attack",
            () -> EntityType.Builder.<AbyssalEdgeAttackEntity>of(AbyssalEdgeAttackEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build(new ResourceLocation(Solaris.MOD_ID, "abyssal_edge_attack").toString()));

    public static final RegistryObject<EntityType<CherryBlossomSeekerEntity>> CHERRY_BLOSSOM_SEEKER = ENTITIES.register("cherry_blossom_seeker",
            () -> EntityType.Builder.<CherryBlossomSeekerEntity>of(CherryBlossomSeekerEntity::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .noSave()
                    .build(new ResourceLocation(Solaris.MOD_ID, "cherry_blossom_seeker").toString()));

    public static final RegistryObject<EntityType<WaterFlowerAttackEntity>> WATER_FLOWER_ATTACK = ENTITIES.register("water_flower_attack",
            () -> EntityType.Builder.<WaterFlowerAttackEntity>of(WaterFlowerAttackEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build(new ResourceLocation(Solaris.MOD_ID, "water_flower_attack").toString()));

    public static final RegistryObject<EntityType<BlizzardAttackEntity>> BLIZZARD_ATTACK = ENTITIES.register("blizzard_attack",
            () -> EntityType.Builder.<BlizzardAttackEntity>of(BlizzardAttackEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build(new ResourceLocation(Solaris.MOD_ID, "blizzard_attack").toString()));

    public static final RegistryObject<EntityType<CloudSplitterAttackEntity>> CLOUD_SPLITTER_ATTACK = ENTITIES.register("cloud_splitter_attack",
            () -> EntityType.Builder.<CloudSplitterAttackEntity>of(CloudSplitterAttackEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build(new ResourceLocation(Solaris.MOD_ID, "cloud_splitter_attack").toString()));
}
