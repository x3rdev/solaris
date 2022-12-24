package com.github.x3rmination.solaris.common.capability.race;

import com.github.x3rmination.solaris.common.solaris.player_race.PlayerRace;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface RaceCapabilityInterface extends INBTSerializable<CompoundTag> {

    PlayerRace getRace();

    void setRace(PlayerRace race);
}
