package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.AbyssalEdge.AbyssalEdgeBladeItem;
import com.github.x3rmination.solaris.common.item.BloodyEclipse.BloodyEclipseItem;
import com.github.x3rmination.solaris.common.item.BloodyFlamberge.BloodyFlambergeItem;
import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaItem;
import com.github.x3rmination.solaris.common.item.FlamingFlamberge.FlamingFlambergeItem;
import com.github.x3rmination.solaris.common.item.Frostbite.FrostbiteItem;
import com.github.x3rmination.solaris.common.item.IceHalberd.IceHalberdItem;
import com.github.x3rmination.solaris.common.item.IceShoulderPad.IceShoulderPadItem;
import com.github.x3rmination.solaris.common.item.MithrilChainsword.MithrilChainswordItem;
import com.github.x3rmination.solaris.common.item.RenderItem;
import com.github.x3rmination.solaris.common.item.SpringWind.SpringWindItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Solaris.MOD_ID);

    public static final RegistryObject<Item> FLAMING_FLAMBERGE = ITEMS.register("flaming_flamberge",
            () -> new FlamingFlambergeItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> FROSTBITE = ITEMS.register("frostbite",
            () -> new FrostbiteItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> FIRE_KATANA = ITEMS.register("fire_katana",
            () -> new FireKatanaItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> ICE_HALBERD = ITEMS.register("ice_halberd",
            () -> new IceHalberdItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> ABYSSAL_EDGE_BLADE = ITEMS.register("abyssal_edge",
            () -> new AbyssalEdgeBladeItem(new Item.Properties().tab(ModItemTab.instance)));

    public static final RegistryObject<Item> ABYSSAL_EDGE_HANDLE = ITEMS.register("abyssal_edge_handle",
            RenderItem::new);

    public static final RegistryObject<Item> MITHRIL_CHAINSWORD = ITEMS.register("mithril_chainsword",
            () -> new MithrilChainswordItem((new Item.Properties().tab(ModItemTab.instance))));

    public static final RegistryObject<Item> BLOODY_ECLIPSE = ITEMS.register("bloody_eclipse",
            () -> new BloodyEclipseItem((new Item.Properties().tab(ModItemTab.instance))));

    public static final RegistryObject<Item> BLOODY_FLAMBERGE = ITEMS.register("bloody_flamberge",
            () -> new BloodyFlambergeItem((new Item.Properties().tab(ModItemTab.instance))));

    public static final RegistryObject<Item> SPRING_WIND = ITEMS.register("spring_wind",
            () -> new SpringWindItem((new Item.Properties().tab(ModItemTab.instance))));

    public static final RegistryObject<Item> ICE_SHOULDER_PAD = ITEMS.register("ice_shoulder_pad",
            () -> new IceShoulderPadItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static class ModItemTab extends CreativeModeTab {
        public static final ModItemTab instance = new ModItemTab(CreativeModeTab.TABS.length, Solaris.MOD_ID);
        private ModItemTab(int index, String tabName) {
            super(index, tabName);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(FLAMING_FLAMBERGE.get());
        }

    }
}

