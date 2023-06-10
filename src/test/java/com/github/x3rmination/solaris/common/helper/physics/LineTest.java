package com.github.x3rmination.solaris.common.helper.physics;

import net.minecraft.world.phys.Vec2;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void intersection() {
        Line l1 = new Line(new Vec2(-1, 0), new Vec2(0, 1));
        Line l2 = new Line(new Vec2(0, 1), new Vec2(1, 0));
        assertTrue(l1.intersection(l2).equals(new Vec2(-1, 1)));
    }

    @Test
    public void point() {
        Line l1 = new Line(new Vec2(0, 0), new Vec2(2, 1));
        assertTrue(l1.point(2).equals(new Vec2(4, 2)));
    }

    @Test
    public void lambda() {
        Line l1 = new Line(new Vec2(0, 0), new Vec2(2, 1));
        assertEquals(2, l1.lambda(new Vec2(4, 2)), 0.0);
    }

    @Test
    public void getDirection() {

    }

    @Test
    public void getOrigin() {
    }
}