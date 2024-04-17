package com.github.x3r.solaris.common.registry;

import com.github.x3r.solaris.Solaris;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.model.armature.HumanoidArmature;
public class AnimationRegistry {

    public static StaticAnimation DEMONIC_AXE;

    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(Solaris.MOD_ID, AnimationRegistry::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        DEMONIC_AXE = new AttackAnimation(0.15F, 0.2F, 0.7F, 0.75F, 1.1F, null, biped.toolR, "biped/skill/demonic_axe", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE);
    }
}
