package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.entity.ElementalEntity;
import com.github.x3r.solaris.common.entity.ScorchedBugEntity;
import com.github.x3r.solaris.common.entity.SnowTrollEntity;
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


    public static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String name, EntityType.EntityFactory<T> entity, MobCategory mobCategory, float width, float height) {
        return ENTITIES.register(name,
                () -> EntityType.Builder.of(entity, mobCategory).sized(width, height).build(name));
    }
}
