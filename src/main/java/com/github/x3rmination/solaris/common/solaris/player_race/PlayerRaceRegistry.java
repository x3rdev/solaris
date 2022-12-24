package com.github.x3rmination.solaris.common.solaris.player_race;

import com.github.x3rmination.solaris.common.solaris.player_class.PlayerClassRegistry;

import java.util.ArrayList;
import java.util.List;

public class PlayerRaceRegistry {

    public static final List<PlayerRace> PLAYER_RACES = new ArrayList<>();

    private PlayerRaceRegistry() {

    }

    public static PlayerRace register(PlayerRace playerRace) {
        PLAYER_RACES.add(playerRace);
        return playerRace;
    }
    public static final PlayerRace HUMAN = register(new PlayerRace("human", PlayerClassRegistry.PLAYER_CLASSES));
    public static final PlayerRace ELF = register(new PlayerRace("elf", List.of(
            PlayerClassRegistry.AQUAMANCER,
            PlayerClassRegistry.UNDINE,
            PlayerClassRegistry.FIRE_MAGE,
            PlayerClassRegistry.NATURE_HEALER,
            PlayerClassRegistry.FIGHTER,
            PlayerClassRegistry.WIND_MAGE,
            PlayerClassRegistry.STORM_MAGE,
            PlayerClassRegistry.CLERIC,
            PlayerClassRegistry.VALKYRIE,
            PlayerClassRegistry.VAMPIRE,
            PlayerClassRegistry.SHADOW
    )));
    public static final PlayerRace TIEFLING = register(new PlayerRace("tiefling", List.of(
            PlayerClassRegistry.FIRE_MAGE,
            PlayerClassRegistry.STORM_MAGE,
            PlayerClassRegistry.FIGHTER,
            PlayerClassRegistry.WIND_MAGE,
            PlayerClassRegistry.DARK_KNIGHT,
            PlayerClassRegistry.NECROMANCER,
            PlayerClassRegistry.SHADOW
    )));
    public static final PlayerRace ORC = register(new PlayerRace("orc", List.of(
            PlayerClassRegistry.ICE_GUARDIAN,
            PlayerClassRegistry.DRAGON_KNIGHT,
            PlayerClassRegistry.STORM_KNIGHT,
            PlayerClassRegistry.DRUID,
            PlayerClassRegistry.GREEN_KNIGHT,
            PlayerClassRegistry.FIGHTER,
            PlayerClassRegistry.PALADIN,
            PlayerClassRegistry.DARK_KNIGHT
    )));
    public static final PlayerRace MINOTAUR = register(new PlayerRace("minotaur", List.of(
            PlayerClassRegistry.ICE_GUARDIAN,
            PlayerClassRegistry.DRAGON_KNIGHT,
            PlayerClassRegistry.STORM_KNIGHT,
            PlayerClassRegistry.DRUID,
            PlayerClassRegistry.GREEN_KNIGHT,
            PlayerClassRegistry.PALADIN,
            PlayerClassRegistry.DARK_KNIGHT
    )));
    public static final PlayerRace FAIRY = register(new PlayerRace("fairy", List.of(
            PlayerClassRegistry.AQUAMANCER,
            PlayerClassRegistry.FIRE_MAGE,
            PlayerClassRegistry.STORM_MAGE,
            PlayerClassRegistry.NATURE_HEALER,
            PlayerClassRegistry.WIND_MAGE,
            PlayerClassRegistry.CLERIC
    )));
    public static final PlayerRace UMBRAL = register(new PlayerRace("umbral", List.of()));

}
