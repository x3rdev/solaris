package com.github.x3r.solaris.common.datagen;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

public class SolarisItemModelGenProvider extends ItemModelProvider {

    public SolarisItemModelGenProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Solaris.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Stream<Item> items = ItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get);
        items.forEach(item -> {
            if(item instanceof ForgeSpawnEggItem) {
                getBuilder(item.toString())
                        .parent(new ModelFile.UncheckedModelFile("minecraft:item/template_spawn_egg"));
            }
        });
    }
}
