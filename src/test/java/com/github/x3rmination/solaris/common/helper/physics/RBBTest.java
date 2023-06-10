package com.github.x3rmination.solaris.common.helper.physics;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import org.junit.Test;

import static org.junit.Assert.*;

public class RBBTest {

    @Test
    public void rotate() {
        Vec2 v = RBB.rotate(new Vec2(1, 0), Math.PI/2);
        assertTrue(v.equals(new Vec2(0, 1)));
    }

    @Test
    public void collidesWith() {
        AABB aabb = new AABB(-1, -1, -1, 1, 1, 1);
        RBB rbb = new RBB(aabb, Math.PI/4);
        assertTrue(rbb.collidesWith(aabb));
    }

    @Test
    public void getAxis() {
    }

    @Test
    public void getCorners() {
    }

    @Test
    public void project() {
        Vec2 point = new Vec2(-1, 2);
        Line axis = new Line(new Vec2(-1, 0), new Vec2(1, 1));
        assertTrue(RBB.project(point, axis).equals(new Vec2(0, 1)));
    }

    @Test
    public void projectionHits() {
    }
}