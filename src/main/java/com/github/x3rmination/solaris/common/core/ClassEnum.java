package com.github.x3rmination.solaris.common.core;

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
