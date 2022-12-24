package com.github.x3rmination.solaris.common.solaris.player_class;

import com.github.x3rmination.solaris.common.solaris.player_affinity.AffinityEnum;

import java.util.ArrayList;
import java.util.List;

public class PlayerClassRegistry {

    public static final List<PlayerClass> PLAYER_CLASSES = new ArrayList<>();

    private PlayerClassRegistry() {

    }

    public static PlayerClass register(PlayerClass playerClass) {
        PLAYER_CLASSES.add(playerClass);
        return playerClass;
    }

    public static final PlayerClass ICE_GUARDIAN = register(new PlayerClass("ice_guardian", AffinityEnum.WATER));
    public static final PlayerClass AQUAMANCER = register(new PlayerClass("aquamancer", AffinityEnum.WATER));
    public static final PlayerClass UNDINE = register(new PlayerClass("undine", AffinityEnum.WATER));

    public static final PlayerClass FIRE_MAGE = register(new PlayerClass("fire_mage", AffinityEnum.FIRE));
    public static final PlayerClass UNFINISHED_1 = register(new PlayerClass("unfinished_1", AffinityEnum.FIRE));
    public static final PlayerClass DRAGON_KNIGHT = register(new PlayerClass("dragon_knight", AffinityEnum.FIRE));

    public static final PlayerClass STORM_MAGE = register(new PlayerClass("storm_mage", AffinityEnum.ENERGY));
    public static final PlayerClass STORM_KNIGHT = register(new PlayerClass("storm_knight", AffinityEnum.ENERGY));
    public static final PlayerClass UNFINISHED_2 = register(new PlayerClass("unfinished_2", AffinityEnum.ENERGY));

    public static final PlayerClass DRUID = register(new PlayerClass("druid", AffinityEnum.NATURE));
    public static final PlayerClass NATURE_HEALER = register(new PlayerClass("nature_healer", AffinityEnum.NATURE));
    public static final PlayerClass GREEN_KNIGHT = register(new PlayerClass("green_knight", AffinityEnum.NATURE));

    public static final PlayerClass FIGHTER = register(new PlayerClass("fighter", AffinityEnum.WIND));
    public static final PlayerClass WIND_MAGE = register(new PlayerClass("wind_mage", AffinityEnum.WIND));
    public static final PlayerClass UNFINISHED_3 = register(new PlayerClass("unfinished_3", AffinityEnum.WIND));

    public static final PlayerClass PALADIN = register(new PlayerClass("paladin", AffinityEnum.LIGHT));
    public static final PlayerClass CLERIC = register(new PlayerClass("cleric", AffinityEnum.LIGHT));
    public static final PlayerClass VALKYRIE = register(new PlayerClass("valkyrie", AffinityEnum.LIGHT));

    public static final PlayerClass DARK_KNIGHT = register(new PlayerClass("dark_knight", AffinityEnum.DARK));
    public static final PlayerClass VAMPIRE = register(new PlayerClass("vampire", AffinityEnum.DARK));
    public static final PlayerClass NECROMANCER = register(new PlayerClass("necromancer", AffinityEnum.DARK));
    public static final PlayerClass SHADOW = register(new PlayerClass("shadow", AffinityEnum.DARK));

}
