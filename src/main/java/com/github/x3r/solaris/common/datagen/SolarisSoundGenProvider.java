package com.github.x3r.solaris.common.datagen;

import com.github.x3r.solaris.Solaris;
import com.github.x3r.solaris.common.registry.SoundRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class SolarisSoundGenProvider extends SoundDefinitionsProvider {

    protected SolarisSoundGenProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Solaris.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(SoundRegistry.SCORCHED_BUG_HURT.get(), definition()
                .subtitle("solaris.subtitle.scorched_bug_hurt")
                .with(SoundDefinition.Sound.sound(
                        new ResourceLocation(Solaris.MOD_ID, "entity/scorched_bug_hurt"),
                        SoundDefinition.SoundType.SOUND)));
        add(SoundRegistry.SCORCHED_BUG_DEATH.get(), definition()
                .subtitle("solaris.subtitle.scorched_bug_death")
                .with(SoundDefinition.Sound.sound(
                        new ResourceLocation(Solaris.MOD_ID, "entity/scorched_bug_death"),
                        SoundDefinition.SoundType.SOUND)));
        add(SoundRegistry.SCORCHED_BUG_STEP.get(), definition()
                .subtitle("solaris.subtitle.scorched_bug_step")
                .with(SoundDefinition.Sound.sound(
                        new ResourceLocation(Solaris.MOD_ID, "entity/scorched_bug_step"),
                        SoundDefinition.SoundType.SOUND)));
        add(SoundRegistry.SCORCHED_BUG_ENTER_BALL.get(), definition()
                .subtitle("solaris.subtitle.scorched_bug_enter_ball")
                .with(SoundDefinition.Sound.sound(
                        new ResourceLocation(Solaris.MOD_ID, "entity/scorched_bug_enter_ball"),
                        SoundDefinition.SoundType.SOUND)));
        add(SoundRegistry.SCORCHED_BUG_EXIT_BALL.get(), definition()
                .subtitle("solaris.subtitle.scorched_bug_exit_ball")
                .with(SoundDefinition.Sound.sound(
                        new ResourceLocation(Solaris.MOD_ID, "entity/scorched_bug_exit_ball"),
                        SoundDefinition.SoundType.SOUND)));
    }
}
