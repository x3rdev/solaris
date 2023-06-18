package com.github.x3rmination.solaris.common.helper.physics;

import com.github.x3rmination.solaris.Solaris;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public List<Entity> getEntitiesInRBB(Entity exclude, Level level) {
        List<Entity> entities = new ArrayList<>();
        double[] minPoint = {this.centerX, this.centerY-this.sizeY/2F, this.centerZ};
        double[] maxPoint = {this.centerX, this.centerY+this.sizeY/2F, this.centerZ};
        Arrays.stream(this.getCorners()).forEach(vec2 -> {
            if(vec2.x < minPoint[0]) {
                minPoint[0] = vec2.x;
            }
            if(vec2.y < minPoint[2]) {
                minPoint[2] = vec2.y;
            }
            if(vec2.x > maxPoint[0]) {
                maxPoint[0] = vec2.x;
            }
            if(vec2.y > maxPoint[2]) {
                maxPoint[2] = vec2.y;
            }
        });
        AABB bigBox = new AABB(new Vec3(minPoint[0], minPoint[1], minPoint[2]), new Vec3(maxPoint[0], maxPoint[1], maxPoint[2]));
        level.getEntities(null, bigBox).forEach(entity -> {
            if(!entity.equals(exclude)) {
                if (this.collidesWith(entity.getBoundingBox())) {
                    entities.add(entity);
                }
            }
        });
        return entities;
    }

    protected static Vec2 rotate(Vec2 vec, double yaw) {
        return new Vec2((float) (Math.cos(yaw) * vec.x - Math.sin(yaw) * vec.y), (float) (Math.sin(yaw) * vec.x + Math.cos(yaw) * vec.y));
    }

    public boolean collidesWith(AABB other) {
        return collidesWith(new RBB(other, 0));
    }

    public boolean collidesWith(RBB other) {
        if (this.centerY + this.sizeY / 2 < other.centerY - other.sizeY / 2 || other.centerY + other.sizeY / 2 < this.centerY - this.sizeY / 2) {
            return false;
        }
//        boolean f0 = this.projectionHits(other);
        boolean f1 = other.projectionHits(this);
        return f1;
    }

    protected Line[] getAxis() {
        Vec2 center = new Vec2((float) this.centerX, (float) this.centerZ);
        return new Line[]{
                new Line(center, rotate(new Vec2(1, 0), yaw)),
                new Line(center, rotate(new Vec2(0, 1), yaw))};
    }

    public Vec2[] getCorners() {
        Vec2 center = new Vec2((float) centerX, (float) centerZ);
        return new Vec2[]{
                center.add(rotate(new Vec2((float) (sizeX / 2F), (float) (sizeZ / 2F)), yaw)),
                center.add(rotate(new Vec2((float) (sizeX / 2F), (float) (-sizeZ / 2F)), yaw)),
                center.add(rotate(new Vec2((float) (-sizeX / 2F), (float) (-sizeZ / 2F)), yaw)),
                center.add(rotate(new Vec2((float) (-sizeX / 2F), (float) (sizeZ / 2F)), yaw))
        };
    }

    protected Vec2[] getMidpoints() {
        Vec2 center = new Vec2((float) centerX, (float) centerZ);
        return new Vec2[]{
                center.add(rotate(new Vec2((float) (sizeX / 2F), 0), yaw)),
                center.add(rotate(new Vec2((float) (-sizeX / 2F), 0), yaw)),
                center.add(rotate(new Vec2(0, (float) (sizeZ / 2F)), yaw)),
                center.add(rotate(new Vec2(0, (float) (-sizeZ / 2F)), yaw))
        };
    }

    protected static Vec2 project(Vec2 point, Line axis) {
        Line normal = new Line(point, new Vec2(axis.getDirection().y, -axis.getDirection().x));
        return axis.intersection(normal);
    }

    protected Vec2[] getExtProj(Line axis) {
        Vec2[] projections = Arrays.stream(this.getCorners()).map(vec2 -> project(vec2, axis)).toArray(Vec2[]::new);
        Vec2 min = projections[0];
        Vec2 max = projections[0];
        for (Vec2 v : projections) {
            if (axis.parameter(v) < axis.parameter(min)) {
                min = v;
            }
            if (axis.parameter(v) > axis.parameter(max)) {
                max = v;
            }
        }
        return new Vec2[]{min, max};
    }

    protected boolean projectionHits(RBB other) {
        Line[] axis = other.getAxis();
        final boolean[] f = {true};
        Arrays.stream(axis).forEach(line -> {
            float pMin = 0;
            float pMax = 0;
            for (int i = 0; i < other.getMidpoints().length; i++) {
                Vec2 vec2 = other.getMidpoints()[i];
                if (!Float.isNaN(line.parameter(vec2))) {
                    float p = line.parameter(vec2);
                    if (p < pMin) pMin = p;
                    if (p > pMax) pMax = p;
                }
            }
            Vec2[] projections = this.getExtProj(line);
            float proj1 = line.parameter(projections[0]);
            float proj2 = line.parameter(projections[1]);
            boolean projectionHits =
                    (proj1 >= pMin && proj1 <= pMax) ||
                    (proj2 >= pMin && proj2 <= pMax) ||
                    (proj1 <= pMin && proj2 >= pMax) ||
                    (proj2 <= pMin && proj1 >= pMax);
            if (!projectionHits) {
                f[0] = false;
            }
        });
        return f[0];
    }
}
