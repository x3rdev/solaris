package com.github.x3r.solaris.common.worldgen.dimension;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.joml.Vector2i;

import java.util.List;

public class IslandNoise {
    private final PerlinSimplexNoise noise;
    private final PerlinSimplexNoise islandNoise;
    private final PerlinSimplexNoise topNoise;
    private final PerlinSimplexNoise bottomNoise;
    private final int cellSize;
    private final float centerOffset;
    private final float islandSize;
    private final int height;
    
    
    /*
        * @param randomSource The random source to use for noise generation
        * @param cellSize The size of the cells in the grid
        * @param centerOffset The offset of the center of the cell from the corner, as a fraction of the cell size
        * @param islandSize The diameter of the island, as a fraction of the cell size
     */
    public IslandNoise(RandomSource randomSource, int cellSize, float centerOffset, float islandSize, int height) {
        this.noise = new PerlinSimplexNoise(randomSource, List.of(-2, 1));
        this.islandNoise = new PerlinSimplexNoise(randomSource, List.of(1, 2, 1));
        this.topNoise = new PerlinSimplexNoise(randomSource, List.of(-6, -1, 1));
        this.bottomNoise = new PerlinSimplexNoise(randomSource, List.of(-3, -1, 1));
        this.cellSize = cellSize;
        this.centerOffset = centerOffset;
        this.islandSize = islandSize;
        this.height = height;
    }

    public int getCellSize() {
        return cellSize;
    }

    public double getValue(int blockX, int blockZ) {
        int closestCenterX = getClosestCenter(blockX, blockZ).x;
        int closestCenterZ = getClosestCenter(blockX, blockZ).y;
        int islandX = closestCenterX/cellSize;
        int islandZ = closestCenterZ/cellSize;
        float angle = (float) Math.atan2(closestCenterZ-blockZ, closestCenterX-blockX);
        double distSquared = (closestCenterX-blockX)*(closestCenterX-blockX) + (closestCenterZ-blockZ)*(closestCenterZ-blockZ);
        double radius = cellSize * 0.75 * islandSize * islandRadiusFunction(islandX, islandZ, normalizedNoiseValue(noise, blockX, blockX), angle);
        double maxDist = cellSize * islandSize;
        return 1 - (float) (distSquared/Mth.square(maxDist + radius));
    }
    public double getValue(int blockX, int blockY, int blockZ) {
        double value = getValue(blockX, blockZ);
        return blockY > height-(4+bottomNoise.getValue(blockX, blockZ, false)*6) && blockY < height+(5+topNoise.getValue(blockX, blockZ, false)*4) ? value : -1;
    }

    public double getIslandValue(int islandX, int islandZ) {
        return normalizedNoiseValue(islandNoise, islandX, islandZ);
    }

    public Vector2i getIslandPos(int blockX, int blockZ) {
        Vector2i closestCenter = getClosestCenter(blockX, blockZ);
        int islandX = closestCenter.x/cellSize;
        int islandZ = closestCenter.y/cellSize;
        return new Vector2i(islandX, islandZ);
    }

    public Vector2i getClosestCenter(int blockX, int blockZ) {
        int cellX = blockX / cellSize;
        int cellZ = blockZ / cellSize;
        Vector2i vec = new Vector2i(blockX, blockZ);
        Vector2i[] cellCenters = {
                getCellCenter(cellX + 1, cellZ),
                getCellCenter(cellX - 1, cellZ),
                getCellCenter(cellX, cellZ + 1),
                getCellCenter(cellX, cellZ - 1),
                getCellCenter(cellX + 1, cellZ + 1),
                getCellCenter(cellX - 1, cellZ + 1),
                getCellCenter(cellX + 1, cellZ - 1),
                getCellCenter(cellX - 1, cellZ - 1)
        };
        Vector2i closestCenter = getCellCenter(cellX, cellZ);
        for (Vector2i cellCenter : cellCenters) {
            if (cellCenter.distanceSquared(vec) < closestCenter.distanceSquared(vec)) {
                closestCenter = cellCenter;
            }
        }
        return closestCenter;
    }

    private Vector2i getCellCenter(int cellX, int cellZ) {
        double theta = Math.PI*noise.getValue(cellX, cellZ, false);
        double dist = cellSize * 0.15F*0;
        int centerX = (int) (cellX * cellSize + cellSize*centerOffset + dist * Math.cos(theta));
        int centerZ = (int) (cellZ * cellSize + cellSize*centerOffset + dist * Math.sin(theta));
        return new Vector2i(centerX, centerZ);
    }

    private double islandRadiusFunction(int islandX, int islandZ, float noise, float angle) {
        float n = normalizedNoiseValue(islandNoise, islandX, islandZ);
        float freq = 2*n+2+(0.1F*noise);
        float phase = (float) (2*Math.PI*n+angle)+noise*0.5F;
        float d = freq*angle+phase;
        float b1 = 6*Mth.sin(3*d)/50*Mth.sin(15*d-2)*Mth.sin(32*d-5);
        float b2 = ((Mth.sin(d)+Mth.cos(2.5F*d))*0.125F);
        return b1+b2;
    }

    private float normalizedNoiseValue(PerlinSimplexNoise noise, int x, int z) {
        return Mth.clamp((Mth.sin((float) (10*noise.getValue(x, z, false)))+1)/2, 0, 1);
    }

}
