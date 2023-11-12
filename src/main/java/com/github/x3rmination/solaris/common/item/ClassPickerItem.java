package com.github.x3rmination.solaris.common.item;

import com.github.x3rmination.solaris.client.gui.ClassSelectionScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ClassPickerItem extends Item {

    public ClassPickerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer instanceof LocalPlayer localPlayer) {
            Minecraft.getInstance().setScreen(new ClassSelectionScreen());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
