package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Solaris.MOD_ID);

    public static final RegistryObject<EntityType<ScorchedBugEntity>> SCORCHED_BUG = registerMob("scorched_bug", ScorchedBugEntity::new, MobCategory.CREATURE, 0.75F, 0.5F);
    public static final RegistryObject<EntityType<ElementalEntity>> ELEMENTAL = registerMob("elemental", ElementalEntity::new, MobCategory.MONSTER, 1.5F, 2F);
    public static final RegistryObject<EntityType<SnowTrollEntity>> SNOW_TROLL = registerMob("snow_troll", SnowTrollEntity::new, MobCategory.MONSTER, 1.75F, 3F);
    public static final RegistryObject<EntityType<ThrownBlockEntity>> THROWN_BLOCK = registerMob("thrown_block", ThrownBlockEntity::new, MobCategory.MISC, 1F, 1F);
    public static final RegistryObject<EntityType<UrborosEntity>> URBOROS = registerMob("urboros", UrborosEntity::new, MobCategory.MONSTER, 2.5F, 2.5F);
    public static final RegistryObject<EntityType<UrborosEphyraEntity>> URBOROS_EPHYRA = registerMob("urboros_ephyra", UrborosEphyraEntity::new, MobCategory.MONSTER, 1F, 1F);
    public static final RegistryObject<EntityType<AncientMechBossEntity>> ANCIENT_MECH_BOSS = registerMob("ancient_mech_boss", AncientMechBossEntity::new, MobCategory.MONSTER, 2F, 4F);

    public static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String name, EntityType.EntityFactory<T> entity, MobCategory mobCategory, float width, float height) {
        return ENTITIES.register(name,
                () -> EntityType.Builder.of(entity, mobCategory).sized(width, height).build(name));
    }
}
