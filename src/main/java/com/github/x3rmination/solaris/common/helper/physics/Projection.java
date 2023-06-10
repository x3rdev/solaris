package com.github.x3rmination.solaris.common.helper.physics;

import net.minecraft.world.phys.Vec2;

public class Projection {

    private final Vec2 p1;
    private final Vec2 p2;
    private final Line axis;

    public Projection(Vec2 p1, Vec2 p2, Line axis) {
        this.p1 = p1;
        this.p2 = p2;
        this.axis = axis;
    }

    public Vec2 getP1() {
        return p1;
    }

    public Vec2 getP2() {
        return p2;
    }

    public Line getAxis() {
        return axis;
    }
}
