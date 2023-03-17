package com.github.x3rmination.solaris.common.item.CloudSplitter;

import com.github.x3rmination.solaris.common.item.PhoenixSpear.PhoenixSpearRenderer;
import com.github.x3rmination.solaris.common.item.SolarisParticleWeapon;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.ForgeTier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class CloudSplitterItem extends SwordItem implements SolarisParticleWeapon, IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public CloudSplitterItem(Properties pProperties) {
        super(new ForgeTier(0, 1000, 2.0F, 0.0F, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)), 6, -3F, pProperties);
    }

    @Override
    public Vector3f[] getParticles() {
        return new Vector3f[]{
                new Vector3f(0, 0, 1.5F),
                new Vector3f(0, 0, 1.0F)
        };
    }

    @Override
    public ParticleOptions getParticleType() {
        return ParticleTypes.EFFECT;
    }

    @Override
    public int getParticleDelay() {
        return 6;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new CloudSplitterRenderer();
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }
}
