package com.github.x3rmination.solaris.common.item.InfernalScimitar;

import com.github.x3rmination.solaris.common.item.SolarisParticleWeapon;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class InfernalScimitarItem extends SwordItem implements SolarisParticleWeapon {
    public InfernalScimitarItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 9, -3F, pProperties);
    }

    @Override
    public Vector3f[] getParticles() {
        return new Vector3f[] {
                new Vector3f(0, 0, 1.0F),
                new Vector3f(0, 0, 0.5F)
        };
    }

    @Override
    public ParticleOptions getParticleType() {
        return ParticleTypes.FLAME;
    }

    @Override
    public int getParticleDelay() {
        return 4;
    }
}
