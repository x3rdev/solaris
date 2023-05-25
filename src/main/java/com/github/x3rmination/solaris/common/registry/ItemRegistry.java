package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.item.AbyssalEdge.AbyssalEdgeBladeItem;
import com.github.x3rmination.solaris.common.item.AmberBlade.AmberBladeItem;
import com.github.x3rmination.solaris.common.item.AstralDagger.AstralDaggerItem;
import com.github.x3rmination.solaris.common.item.BloodyEclipse.BloodyEclipseItem;
import com.github.x3rmination.solaris.common.item.BloodyFlamberge.BloodyFlambergeItem;
import com.github.x3rmination.solaris.common.item.CentipedeScaleArmor.CentipedeScaleArmorItem;
import com.github.x3rmination.solaris.common.item.CloudSplitter.CloudSplitterItem;
import com.github.x3rmination.solaris.common.item.CometSpear.CometSpearItem;
import com.github.x3rmination.solaris.common.item.DemonicKatana.DemonicKatana;
import com.github.x3rmination.solaris.common.item.DemonsBlade.DemonsBladeItem;
import com.github.x3rmination.solaris.common.item.FireKatana.FireKatanaItem;
import com.github.x3rmination.solaris.common.item.FlamingFlamberge.FlamingFlambergeItem;
import com.github.x3rmination.solaris.common.item.Frostbite.FrostbiteItem;
import com.github.x3rmination.solaris.common.item.HolyBlade.HolyBladeItem;
import com.github.x3rmination.solaris.common.item.IceDragonSword.IceDragonSwordItem;
import com.github.x3rmination.solaris.common.item.IceHalberd.IceHalberdItem;
import com.github.x3rmination.solaris.common.item.IceShoulderPad.IceShoulderPadItem;
import com.github.x3rmination.solaris.common.item.IceZweihaender.IceZweihaenderItem;
import com.github.x3rmination.solaris.common.item.InfernalScimitar.InfernalScimitarItem;
import com.github.x3rmination.solaris.common.item.LightClaymore.LightClaymoreItem;
import com.github.x3rmination.solaris.common.item.LivingGlaive.LivingGlaiveItem;
import com.github.x3rmination.solaris.common.item.LivingGreatsword.LivingGreatswordItem;
import com.github.x3rmination.solaris.common.item.LivingShield.LivingShieldItem;
import com.github.x3rmination.solaris.common.item.MithrilChainsword.MithrilChainswordItem;
import com.github.x3rmination.solaris.common.item.PhoenixShield.PhoenixShieldItem;
import com.github.x3rmination.solaris.common.item.PhoenixSpear.PhoenixSpearItem;
import com.github.x3rmination.solaris.common.item.RavensBlade.RavensBladeItem;
import com.github.x3rmination.solaris.common.item.RenderItem;
import com.github.x3rmination.solaris.common.item.RuinedBlade.RuinedBladeItem;
import com.github.x3rmination.solaris.common.item.SolarArmor.SolarArmorItem;
import com.github.x3rmination.solaris.common.item.SpringWind.SpringWindItem;
import com.github.x3rmination.solaris.common.item.WaterFlower.WaterFlowerItem;
import com.github.x3rmination.solaris.common.item.WitherClaymore.WitherClaymoreItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Solaris.MOD_ID);

    public static final RegistryObject<Item> SOLARIS_SUN = ITEMS.register("solaris",
            () -> new Item((new Item.Properties()).tab(ModItemTab.instance)));
    public static final RegistryObject<Item> SOLARIS_SUN_AURA = ITEMS.register("solaris_aura",
            () -> new Item((new Item.Properties()).tab(ModItemTab.instance)));
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

    public static final RegistryObject<Item> PHOENIX_SPEAR = ITEMS.register("phoenix_spear",
            () -> new PhoenixSpearItem((new Item.Properties().tab(ModItemTab.instance))));
    public static final RegistryObject<Item> PHOENIX_SHIELD = ITEMS.register("phoenix_shield",
            () -> new PhoenixShieldItem((new Item.Properties().tab(ModItemTab.instance))));
    public static final RegistryObject<Item> ICE_SHOULDER_PAD = ITEMS.register("ice_shoulder_pad",
            () -> new IceShoulderPadItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> SOLAR_HELMET = ITEMS.register("solar_helmet",
            () -> new SolarArmorItem(EquipmentSlot.HEAD, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> SOLAR_CHESTPLATE = ITEMS.register("solar_chestplate",
            () -> new SolarArmorItem(EquipmentSlot.CHEST, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> SOLAR_LEGGINGS = ITEMS.register("solar_leggings",
            () -> new SolarArmorItem(EquipmentSlot.LEGS, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> SOLAR_BOOTS = ITEMS.register("solar_boots",
            () -> new SolarArmorItem(EquipmentSlot.FEET, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> CLOUD_SPLITTER = ITEMS.register("cloud_splitter",
            () -> new CloudSplitterItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> RAVENS_BLADE = ITEMS.register("ravens_blade",
            () -> new RavensBladeItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> ICE_ZWEIHAENDER = ITEMS.register("ice_zweihaender",
            () -> new IceZweihaenderItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> LIGHT_CLAYMORE = ITEMS.register("light_claymore",
            () -> new LightClaymoreItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> RUINED_BLADE = ITEMS.register("ruined_blade",
            () -> new RuinedBladeItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> WITHER_CLAYMORE = ITEMS.register("wither_claymore",
            () -> new WitherClaymoreItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> AMBER_BLADE = ITEMS.register("amber_blade",
            () -> new AmberBladeItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> COMET_SPEAR = ITEMS.register("comet_spear",
            () -> new CometSpearItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> INFERNAL_SCIMITAR = ITEMS.register("infernal_scimitar",
            () -> new InfernalScimitarItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> HOLY_BLADE = ITEMS.register("holy_blade",
            () -> new HolyBladeItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> ASTRAL_DAGGER = ITEMS.register("astral_dagger",
            () -> new AstralDaggerItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> DEMONS_BLADE = ITEMS.register("demons_blade",
            () -> new DemonsBladeItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> ICE_DRAGON_SWORD = ITEMS.register("ice_dragon_sword",
            () -> new IceDragonSwordItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> LIVING_GREATSWORD = ITEMS.register("living_greatsword",
            () -> new LivingGreatswordItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> LIVING_SHIELD = ITEMS.register("living_shield",
            () -> new LivingShieldItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> LIVING_GLAIVE = ITEMS.register("living_glaive",
            () -> new LivingGlaiveItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> WATER_FLOWER = ITEMS.register("water_flower",
            () -> new WaterFlowerItem((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> DEMONIC_KATANA = ITEMS.register("demonic_katana",
            () -> new DemonicKatana((new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_HELMET = ITEMS.register("centipede_scale_helmet",
            () -> new CentipedeScaleArmorItem(EquipmentSlot.HEAD, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_CHESTPLATE = ITEMS.register("centipede_scale_chestplate",
            () -> new CentipedeScaleArmorItem(EquipmentSlot.CHEST, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_LEGGINGS = ITEMS.register("centipede_scale_leggings",
            () -> new CentipedeScaleArmorItem(EquipmentSlot.LEGS, (new Item.Properties()).tab(ModItemTab.instance)));

    public static final RegistryObject<Item> CENTIPEDE_SCALE_BOOTS = ITEMS.register("centipede_scale_boots",
            () -> new CentipedeScaleArmorItem(EquipmentSlot.FEET, (new Item.Properties()).tab(ModItemTab.instance)));

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

