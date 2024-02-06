package com.github.x3r.solaris.common.worldgen.dimension;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.phys.Vec2;

import java.util.List;

public class IslandNoise {
    private final int cellSize;

    private final PerlinSimplexNoise noise;

    public IslandNoise(long seed, int cellSize) {
        this(RandomSource.create(seed), cellSize);
    }

    public IslandNoise(RandomSource randomSource, int cellSize) {
        this.cellSize = cellSize;
        this.noise = new PerlinSimplexNoise(randomSource, List.of(-2, 1));
    }

    public double getValue(int x, int y) {
        int cellX = x/cellSize;
        int cellY = y/cellSize;
        Vec2 vec = new Vec2(x, y);
        Vec2[] cellCenters = {
                getCellCenter(cellX+1, cellY),
                getCellCenter(cellX-1, cellY),
                getCellCenter(cellX, cellY+1),
                getCellCenter(cellX, cellY-1),
                getCellCenter(cellX+1, cellY+1),
                getCellCenter(cellX-1, cellY+1),
                getCellCenter(cellX+1, cellY-1),
                getCellCenter(cellX-1, cellY-1)
        };
        Vec2 closestCenter = getCellCenter(cellX, cellY);
        for (Vec2 cellCenter : cellCenters) {
            if(cellCenter.distanceToSqr(vec) < closestCenter.distanceToSqr(vec)) {
                closestCenter = cellCenter;
            }
        }
        double theta = Math.atan2(closestCenter.y-y, closestCenter.x-x);
        double freq = (noise.getValue(closestCenter.x, closestCenter.y, true)*2.7+2.6)/
                (noise.getValue(closestCenter.x+theta, closestCenter.y+theta, false)+1.4)   ;
        double phase = noise.getValue(x, y, false)*1.3;
        double scale = islandRandomSize(cellX, cellY) * islandSineFunction(theta, freq, phase);
        double dist = Mth.clamp(((closestCenter.x-x)*(closestCenter.x-x) + (closestCenter.y-y)*(closestCenter.y-y))/(cellSize*cellSize*scale), 0, 1);
        return 1-dist;
    }

    private Vec2 getCellCenter(int cellX, int cellY) {
        double theta = Math.PI*noise.getValue(cellX, cellY, false);
        int centerX = (int) (cellX * cellSize + cellSize/2F + Math.cos(theta)*cellSize/(3)*0);
        int centerZ = (int) (cellY * cellSize + cellSize/2F + Math.sin(theta)*cellSize/(3)*0);
        return new Vec2(centerX, centerZ);
    }

    private double islandRandomSize(int cellX, int cellY) {
        double n = Mth.clamp((noise.getValue(cellX, cellY, false)+1)/2, 0, 1);
        return n * 3.5;
    }

    private double islandSineFunction(double theta, double freq, double phase) {
        double d = theta*freq+phase;
        double r = (Math.sin(d)+Math.cos(3*d+2))/1.991;
        return ((r+1)/2)*0.15+0.055;
    }
}
