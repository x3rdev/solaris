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
        this.islandNoise = new PerlinSimplexNoise(randomSource, List.of(1, 2, 1));
    }

    public double getValue(int blockX, int blockY) {
        Vector2i closestCenter = getClosestCenter(blockX, blockY);
        int islandX = closestCenter.x/cellSize;
        int islandY = closestCenter.y/cellSize;
        float angle = (float) Math.atan2(closestCenter.y-blockY, closestCenter.x-blockX);
        double distSquared = (closestCenter.x-blockX)*(closestCenter.x-blockX) + (closestCenter.y-blockY)*(closestCenter.y-blockY);
        double radius = cellSize * 0.4 * islandRadiusFunction(islandX, islandY, normalizedNoiseValue(noise, blockX, blockX), angle);
        double maxDist = cellSize * 0.75;
        return 1 - Mth.sqrt((float) distSquared)/(maxDist + radius);
    }

    public double getIslandValue(int islandX, int islandY) {
        return normalizedNoiseValue(islandNoise, islandX, islandY);
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
        double dist = cellSize * 0.15F;
        int centerX = (int) (cellX * cellSize + cellSize/2F + dist * Math.cos(theta));
        int centerZ = (int) (cellY * cellSize + cellSize/2F + dist * Math.sin(theta));
        return new Vector2i(centerX, centerZ);
    }

    private double islandRadiusFunction(int islandX, int islandY, float noise, float angle) {
        float n = normalizedNoiseValue(islandNoise, islandX, islandY);
        float freq = 2*n+2+(0.1F*noise);
        float phase = (float) (2*Math.PI*n+angle)+noise*0.5F;
        float d = freq*angle+phase;
        float b1 = 6*Mth.sin(3*d)/50*Mth.sin(15*d-2)*Mth.sin(32*d-5);
        float b2 = ((Mth.sin(d)+Mth.cos(2.5F*d))*0.125F);
        return b1+b2;
    }

    private float normalizedNoiseValue(PerlinSimplexNoise noise, int x, int y) {
        return Mth.clamp((Mth.sin((float) (10*noise.getValue(x, y, false)))+1)/2, 0, 1);
    }
}
