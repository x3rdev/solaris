package com.github.x3rmination.solaris.common.item.AbyssalEdge;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Solaris.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class AbyssalEdgeHandleItem extends Item {
    public AbyssalEdgeHandleItem(Properties pProperties) {
        super(pProperties);
    }
}
