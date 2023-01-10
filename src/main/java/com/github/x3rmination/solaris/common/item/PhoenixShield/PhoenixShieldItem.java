package com.github.x3rmination.solaris.common.item.PhoenixShield;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class PhoenixShieldItem extends ShieldItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public PhoenixShieldItem(Properties pProperties) {
        super(pProperties);
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
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return new PhoenixShieldRenderer();
            }
        });
    }
}
