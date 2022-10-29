package com.github.x3rmination.solaris.common.item.SteamCounter.blade;

import com.github.x3rmination.solaris.common.item.RenderItem;
import com.github.x3rmination.solaris.common.item.SteamCounter.SteamCounterRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;

import java.util.function.Consumer;

public class SteamCounterBladeItem extends RenderItem {
    public SteamCounterBladeItem() {
        super();
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new SteamCounterBladeRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }
}
