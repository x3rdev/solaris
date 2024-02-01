package com.github.x3r.solaris.common.worldgen.dimension;

import it.unimi.dsi.fastutil.Pair;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.phys.Vec2;

import java.util.List;

public class IslandNoise {

    private final RandomSource randomSource;
    private final int cellSize;

    private final PerlinSimplexNoise noise;

    public IslandNoise(long seed, int cellSize) {
        this(RandomSource.create(seed), cellSize);
    }

    public IslandNoise(RandomSource randomSource, int cellSize) {
        this.randomSource = randomSource;
        this.cellSize = cellSize;
        this.noise = new PerlinSimplexNoise(randomSource, List.of(5, 1));
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
        double phase = 0;
        double scale = Mth.clamp((Math.cos(theta*5)+1)/2, 0.2, 0.5);
        double dist = (double)((closestCenter.x-x)*(closestCenter.x-x) + (closestCenter.y-y)*(closestCenter.y-y))/(cellSize*cellSize*scale);
        return 1-dist;
//        return x==centerX&&z==centerZ?0:1;
    }

    private Vec2 getCellCenter(int cellX, int cellY) {
        double theta = Math.PI*noise.getValue(cellX, cellY, false);
        int centerX = (int) (cellX * cellSize + cellSize/2F + Math.cos(theta)*cellSize/(2));
        int centerZ = (int) (cellY * cellSize + cellSize/2F + Math.sin(theta)*cellSize/(2));
        return new Vec2(centerX, centerZ);
    }
}
