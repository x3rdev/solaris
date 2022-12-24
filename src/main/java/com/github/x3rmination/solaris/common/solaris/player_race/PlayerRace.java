package com.github.x3rmination.solaris.common.solaris.player_race;

import com.github.x3rmination.solaris.common.solaris.player_affinity.AffinityEnum;
import com.github.x3rmination.solaris.common.solaris.player_class.PlayerClass;

import java.util.List;

public class PlayerRace {

    private final String name;
    private final List<PlayerClass> classList;
    public PlayerRace(String name, List<PlayerClass> classList) {
        this.name = name;
        this.classList = classList;
    }
    public String getDescriptionKey() {
        return "solaris.race." + this.name + ".description";
    }

    public List<PlayerClass> getClasses() {
        return this.classList;
    }
    public boolean canHaveAffinity(AffinityEnum affinity) {
        return !this.getClasses().stream().filter(playerClass -> playerClass.getAffinity() == affinity).toList().isEmpty();
    }

    public static PlayerRace fromString(String race) {
        for(PlayerRace playerRace : PlayerRaceRegistry.PLAYER_RACES) {
            if(playerRace.name.equals(race)) {
                return playerRace;
            }
        }
        return PlayerRaceRegistry.HUMAN;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
