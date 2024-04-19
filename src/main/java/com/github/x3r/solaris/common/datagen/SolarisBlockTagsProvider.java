package com.github.x3r.solaris.common.datagen;

import com.github.x3r.solaris.Solaris;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SolarisBlockTagsProvider extends BlockTagsProvider {

    public static final TagKey<Block> SNOW_TROLL_THROWABLE = TagKey.create(Registries.BLOCK, new ResourceLocation(Solaris.MOD_ID, "snow_troll_throwable"));
    public SolarisBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Solaris.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(SNOW_TROLL_THROWABLE).add(Blocks.SNOW_BLOCK);
    }
}
