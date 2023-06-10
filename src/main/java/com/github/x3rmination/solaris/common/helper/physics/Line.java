package com.github.x3rmination.solaris.common.helper.physics;

import net.minecraft.world.phys.Vec2;

public class Line {

    private Vec2 origin;
    private Vec2 direction;

    public Line(Vec2 origin, Vec2 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public static Line from2Points(Vec2 p0, Vec2 p1) {
        return new Line(p0, p1.add(p0.negated()));
    }

    public Vec2 intersection(Line other) {

        //Cramer's law

        float a1 = this.direction.y;
        float b1 = -this.direction.x;
        float c1 = -(a1*this.origin.x+b1*this.origin.y);

        float a2 = other.direction.y;
        float b2 = -other.direction.x;
        float c2 = -(a2*other.origin.x+b2*other.origin.y);

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

    //TODO check if != is too strict in this case
    public float lambda(Vec2 point) {
        float c = point.add(this.origin.negated()).x / this.direction.x;
        if(direction.y * c + origin.y != point.y) {
            return Float.NaN;
        }
        return c;
    }

    public Vec2 getDirection() {
        return direction;
    }

    public Vec2 getOrigin() {
        return origin;
    }

    public void setDirection(Vec2 direction) {
        this.direction = direction;
    }

    public void setOrigin(Vec2 origin) {
        this.origin = origin;
    }
}
