package com.github.x3rmination.solaris.common.helper.physics;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Rotated Bounding Box, yaw only, around center
public class RBB {

    public final double centerX;
    public final double centerY;
    public final double centerZ;
    public final double sizeX;
    public final double sizeY;
    public final double sizeZ;
    public final double yaw;

    public RBB(AABB aabb, double yaw) {
        this.centerX = aabb.getCenter().x;
        this.centerY = aabb.getCenter().y;
        this.centerZ = aabb.getCenter().z;
        this.sizeX = aabb.getXsize();
        this.sizeY = aabb.getYsize();
        this.sizeZ = aabb.getZsize();
        this.yaw = yaw;
    }

    private Vec2 rotate(Vec2 vec, double yaw) {
        return new Vec2(Math.round((float) (Math.cos(yaw)*vec.x - Math.sin(yaw)*vec.y)), Math.round((float) (Math.sin(yaw)*vec.x + Math.cos(yaw)*vec.y)));
    }

    public boolean collidesWith(AABB other) {
        return collidesWith(new RBB(other, 0));
    }

    public boolean collidesWith(RBB other) {
        if(this.centerY + this.sizeY/2 < other.centerY - other.sizeY/2 || other.centerY + other.sizeY/2 < this.centerY - this.sizeY/2) {
            return false;
        }
        Line[] axis0 = this.getAxis();
        Line[] axis1 = other.getAxis();

        List<Vec2> proj00 = new ArrayList<>(); // bb 0, axis 0
        List<Vec2> proj01 = new ArrayList<>();
        List<Vec2> proj10 = new ArrayList<>();
        List<Vec2> proj11 = new ArrayList<>();

        Arrays.stream(this.getCorners()).forEach(vec2 -> {
            Vec2 p1 = project(vec2, axis1[0]);
            Vec2 p2 = project(vec2, axis1[1]);
            if(p1.distanceToSqr(vec2) > p2.distanceToSqr(vec2)) {
                if(proj00.size() == 2) {
                    proj01.add(p1);
                } else {
                    proj00.add(p1);
                }
            } else {
                if(proj01.size() == 2) {
                    proj00.add(p1);
                } else {
                    proj01.add(p1);
                }
            }
        });
        Arrays.stream(other.getCorners()).forEach(vec2 -> {
            Vec2 p1 = project(vec2, axis0[0]);
            Vec2 p2 = project(vec2, axis0[1]);
            if(p1.distanceToSqr(vec2) > p2.distanceToSqr(vec2)) {
                if(proj10.size() == 2) {
                    proj11.add(p1);
                } else {
                    proj10.add(p1);
                }
            } else {
                if(proj11.size() == 2) {
                    proj10.add(p1);
                } else {
                    proj11.add(p1);
                }
            }
        });

        boolean f00 = projectionHits(new Projection(proj00.get(0), proj00.get(1), axis0[0]));
        boolean f01 = projectionHits(new Projection(proj01.get(0), proj01.get(1), axis0[1]));
        boolean f10 = projectionHits(new Projection(proj10.get(0), proj10.get(1), axis0[0]));
        boolean f11 = projectionHits(new Projection(proj11.get(0), proj11.get(1), axis0[1]));

        return f00 && f01 && f10 && f11;
    }

    private Line[] getAxis() {
        Vec2 center = new Vec2((float) this.centerX, (float) this.centerZ);
        return new Line[]{
                new Line(center, rotate(new Vec2(1, 0), yaw)),
                new Line(center, rotate(new Vec2(0, 1), yaw))};
    }

    private Vec2[] getCorners() {
        return new Vec2[]{
                rotate(new Vec2((float) (centerX + sizeX/2F), (float) (centerZ + sizeZ/2F)), yaw),
                rotate(new Vec2((float) (centerX + sizeX/2F), (float) (centerZ - sizeZ/2F)), yaw),
                rotate(new Vec2((float) (centerX - sizeX/2F), (float) (centerZ - sizeZ/2F)), yaw),
                rotate(new Vec2((float) (centerX - sizeX/2F), (float) (centerZ + sizeZ/2F)), yaw)
        };
    }

    private Vec2 project(Vec2 point, Line axis) {
        Line normal = new Line(point, new Vec2(axis.getDirection().y, -axis.getDirection().x));
        return axis.intersection(normal);
    }

    private boolean projectionHits(Projection projection) {
        float p1D = projection.getP1().distanceToSqr(new Vec2((float) centerX, (float) centerZ));
        float p2D = projection.getP2().distanceToSqr(new Vec2((float) centerX, (float) centerZ));
        float sideD;
        if(projection.getAxis().equals(getAxis()[0])) {
            sideD = (float) (sizeX/2);
        } else {
            sideD = (float) (sizeZ/2);
        }
        return (p1D < sideD && p2D > sideD) || (p1D > sideD && p2D < sideD);
    }
}
