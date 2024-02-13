package com.github.x3r.solaris.common.worldgen.dimension;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.joml.Vector2i;

import java.util.List;

public class IslandNoise {
    public final int cellSize;
    private final PerlinSimplexNoise noise;
    private final PerlinSimplexNoise islandNoise;
    public IslandNoise(long seed, int cellSize) {
        this(RandomSource.create(seed), cellSize);
    }

    public IslandNoise(RandomSource randomSource, int cellSize) {
        this.cellSize = cellSize;
        this.noise = new PerlinSimplexNoise(randomSource, List.of(-2, 1));
        this.islandNoise = new PerlinSimplexNoise(randomSource, List.of(1, 1, 1));
    }

    public double getValue(int blockX, int blockY) {
        Vector2i closestCenter = getClosestCenter(blockX, blockY);
        int islandX = closestCenter.x/cellSize;
        int islandY = closestCenter.y/cellSize;
        float angle = (float) Mth.atan2(closestCenter.y-blockY, closestCenter.x-blockX);
        double distSquared = (closestCenter.x-blockX)*(closestCenter.x-blockX) + (closestCenter.y-blockY)*(closestCenter.y-blockY);
        double radius = cellSize * 0.03 * islandRadiusFunction(islandX, islandY, angle);
        double maxDist = cellSize * 0.25 + radius;
        return distSquared < maxDist*maxDist ? 1 : 0;
    }

    public double getIslandValue(int islandX, int islandY) {
        return normalizedNoiseValue(islandNoise, islandX, islandY, false);
    }

    public Vector2i getIslandPos(int blockX, int blockY) {
        Vector2i closestCenter = getClosestCenter(blockX, blockY);
        int islandX = closestCenter.x/cellSize;
        int islandY = closestCenter.y/cellSize;
        return new Vector2i(islandX, islandY);
    }

    private Vector2i getClosestCenter(int blockX, int blockY) {
        int cellX = blockX / cellSize;
        int cellY = blockY / cellSize;
        Vector2i vec = new Vector2i(blockX, blockY);
        Vector2i[] cellCenters = {
                getCellCenter(cellX + 1, cellY),
                getCellCenter(cellX - 1, cellY),
                getCellCenter(cellX, cellY + 1),
                getCellCenter(cellX, cellY - 1),
                getCellCenter(cellX + 1, cellY + 1),
                getCellCenter(cellX - 1, cellY + 1),
                getCellCenter(cellX + 1, cellY - 1),
                getCellCenter(cellX - 1, cellY - 1)
        };
        Vector2i closestCenter = getCellCenter(cellX, cellY);
        for (Vector2i cellCenter : cellCenters) {
            if (cellCenter.distanceSquared(vec) < closestCenter.distanceSquared(vec)) {
                closestCenter = cellCenter;
            }
        }
        return closestCenter;
    }

    private Vector2i getCellCenter(int cellX, int cellY) {
        double theta = Math.PI*noise.getValue(cellX, cellY, false);
        double dist = cellSize/3F;
        int centerX = (int) (cellX * cellSize + cellSize/2F + Math.cos(theta)*dist*0);
        int centerZ = (int) (cellY * cellSize + cellSize/2F + Math.sin(theta)*dist*0);
        return new Vector2i(centerX, centerZ);
    }

    private double islandRadiusFunction(int islandX, int islandY, float angle) {
//        double freq = normalizedNoiseValue(islandNoise, islandX, islandY, false);
//        double phase = normalizedNoiseValue(islandNoise, randomizeNumber(islandX), islandY, false)*Math.PI;
//        float d = (float) (angle/2*freq+phase);
//        double r = Mth.abs(Mth.sin(d)*Mth.sin(5*d)*Mth.sin(3*d)*Mth.sin(2*d));
        int sineCount = 2;
        double r = 0;
        for (int i = 0; i < sineCount; i++) {
            double frequency = 10 * normalizedNoiseValue(islandNoise, randomizeNumber(islandX+i), randomizeNumber(islandY+i), true);
            double phase = 4*angle;
            r+=Math.sin((frequency)*angle+(phase));
        }
        return r;
    }

    private double normalizedNoiseValue(PerlinSimplexNoise noise, int x, int y, boolean useNoiseOffsets) {
        return Mth.clamp((Mth.sin((float) (Math.PI*islandNoise.getValue(x, y, useNoiseOffsets)))+1)/2, 0, 1);
    }

    private int randomizeNumber(int i) {
        final long multiplier = 0x5DEECE66DL;
        final long addend = 0xBL;
        final long mask = (1L << 48) - 1;
        return (int) ((i * multiplier + addend) & mask);
    }
}
