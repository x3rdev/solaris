package com.github.x3rmination.solaris.common.item.LivingShield;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class LivingShieldItem extends ShieldItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public LivingShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

//    public UseAnim getUseAnimation(ItemStack pStack) {
//        return UseAnim.BLOCK;
//    }
//
//    public int getUseDuration(ItemStack pStack) {
//        return 72000;
//    }
//
//    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
//        ItemStack itemstack = pPlayer.getItemInHand(pHand);
//        pPlayer.startUsingItem(pHand);
//        return InteractionResultHolder.consume(itemstack);
//    }
//
    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return new LivingShieldRenderer();
            }
        });
    }
//
//    @Override
//    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
//        return net.minecraftforge.common.ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
//    }
}
