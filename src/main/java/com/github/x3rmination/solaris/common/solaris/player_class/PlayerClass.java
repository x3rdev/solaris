package com.github.x3rmination.solaris.common.solaris.player_class;

import com.github.x3rmination.solaris.common.solaris.player_affinity.AffinityEnum;

public class PlayerClass {

    private final String name;
    private final AffinityEnum affinity;
    public PlayerClass(String name, AffinityEnum affinity) {
        this.name = name;
        this.affinity = affinity;
    }
    public String getDescriptionKey() {
        return "solaris.class." + this.name + ".description";
    }
    public AffinityEnum getAffinity() {
        return this.affinity;
    }

    public String getName() {
        return this.name;
    }
}
