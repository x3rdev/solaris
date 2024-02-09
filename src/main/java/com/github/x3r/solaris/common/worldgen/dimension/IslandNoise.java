package com.github.x3r.solaris.common.worldgen.dimension;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.joml.Vector2i;

import java.util.Arrays;
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
        this.islandNoise = new PerlinSimplexNoise(randomSource, List.of(3, 1));
    }

    public double getValue(int x, int y) {
        Vector2i closestCenter = getIslandPos(x, y);
        int islandX = closestCenter.x/cellSize;
        int islandY = closestCenter.y/cellSize;
        double angle = Math.atan2(closestCenter.y-y, closestCenter.x-x);
        double scale = islandScaleFunction(islandX, islandY, angle);
        double size = islandRandomSize(islandX, islandY);
        double dist = ((closestCenter.x-x)*(closestCenter.x-x) + (closestCenter.y-y)*(closestCenter.y-y))/(cellSize*cellSize/4F);
        double val = dist/(size*scale);
        return 1-Mth.clamp(val, 0, 1);
    }

    public double getIslandValue(int islandX, int islandY) {
        return Mth.clamp((Math.sin(Math.PI*islandNoise.getValue(islandX, islandY, false))+1)/2, 0, 1);
    }

    private Vector2i getCellCenter(int cellX, int cellY) {
        double theta = Math.PI*noise.getValue(cellX, cellY, false);
        double dist = cellSize/3F;
        int centerX = (int) (cellX * cellSize + cellSize/2F + Math.cos(theta)*dist);
        int centerZ = (int) (cellY * cellSize + cellSize/2F + Math.sin(theta)*dist);
        return new Vector2i(centerX, centerZ);
    }

    public Vector2i getIslandPos(int x, int y) {
        int cellX = x / cellSize;
        int cellY = y / cellSize;
        Vector2i vec = new Vector2i(x, y);
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

    private double islandRandomSize(int islandX, int islandY) {
        double n = (Math.sin(Math.PI*islandNoise.getValue(islandX, islandY, false))+1)/2;
        return Mth.clamp(n*0.5+0.7, 0.7, 1.2);
    }

    private double islandScaleFunction(int islandX, int islandY, double angle) {
        double[] angles = islandBiasAngles(islandX, islandY);
        double closestBias = angles[0];
        for (double v : angles) {
            if(Mth.degreesDifferenceAbs((float) angle, (float) v) < Mth.degreesDifferenceAbs((float) angle, (float) closestBias)) {
                closestBias = v;
            }
        }
        return Math.abs((closestBias-angle)/closestBias);
    }

    private double[] islandBiasAngles(int islandX, int islandY) {
        double n = (Math.sin(Math.PI*islandNoise.getValue(islandX, islandY, false))+1)/2;
        int size = (int) (Math.round(n * 2) + 2); // 2 - 4 spikes
        double[] array = new double[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.PI*noise.getValue(randomizeNumber(islandX), randomizeNumber(islandY), false);
        }
        return array;
    }

    private int randomizeNumber(int i) {
        final long multiplier = 0x5DEECE66DL;
        final long addend = 0xBL;
        final long mask = (1L << 48) - 1;
        return (int) ((i * multiplier + addend) & mask);
    }
}
