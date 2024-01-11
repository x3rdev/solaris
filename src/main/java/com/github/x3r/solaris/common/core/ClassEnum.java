package com.github.x3r.solaris.common.core;

public enum ClassEnum {

    ELEMENTALIST,
    CLERIC,
    SHADOW,
    VALKYRIE,
    RANGER,
    DRUID,
    VAMPIRE,
    NECROMANCER,
    DARK_KNIGHT,
    PALADIN;

    public static ClassEnum DEFAULT_CLASS() {
        return ELEMENTALIST;
    }
}
