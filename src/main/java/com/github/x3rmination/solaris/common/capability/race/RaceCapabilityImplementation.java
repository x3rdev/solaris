package com.github.x3rmination.solaris.common.capability.race;

import com.github.x3rmination.solaris.common.solaris.player_race.PlayerRace;
import com.github.x3rmination.solaris.common.solaris.player_race.PlayerRaceRegistry;
import net.minecraft.nbt.CompoundTag;

public class RaceCapabilityImplementation implements RaceCapabilityInterface {

    private static final String NBT_KEY_RACE = "race";

    private String race = PlayerRaceRegistry.HUMAN.toString();

    @Override
    public PlayerRace getRace() {
        return PlayerRace.fromString(this.race);
    }

    @Override
    public void setRace(PlayerRace race) {
        this.race = race.toString();
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putString(NBT_KEY_RACE, this.race);
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.race = nbt.getString(NBT_KEY_RACE);
    }
}
