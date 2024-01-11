package com.github.x3r.solaris.common.helper.physics;

import net.minecraft.world.phys.Vec2;

public class Line {

    private final Vec2 origin;
    private final Vec2 direction;

    public Line(Vec2 origin, Vec2 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vec2 intersection(Line other) {

        float a1 = this.direction.y;
        float b1 = -this.direction.x;
        float c1 = (a1*this.origin.x+b1*this.origin.y);

        float a2 = other.direction.y;
        float b2 = -other.direction.x;
        float c2 = (a2*other.origin.x+b2*other.origin.y);

        float d = a1*b2-a2*b1;
        float dX = c1*b2-c2*b1;
        float dY = a1*c2-a2*c1;

        if(d == 0) {
            return new Vec2(Float.NaN, Float.NaN);
        }

        float x = dX/d;
        float y = dY/d;

        return new Vec2(x, y);
    }

    public Vec2 point(float lambda) {
        return new Vec2(this.origin.x + lambda * this.direction.x, this.origin.y + lambda * this.direction.y);
    }

    public float parameter(Vec2 point) {
        float a = (point.x-this.origin.x)/this.direction.x;
        float b = (point.y-this.origin.y)/this.direction.y;
        if(Float.isNaN(a) || Float.isInfinite(a)) return b;
        if(Float.isNaN(b) || Float.isInfinite(b)) return a;
        return ((int) a * 1000) == ((int) b * 1000) ? a : Float.NaN;
    }

    public Vec2 getDirection() {
        return direction;
    }

    public Vec2 getOrigin() {
        return origin;
    }
}
