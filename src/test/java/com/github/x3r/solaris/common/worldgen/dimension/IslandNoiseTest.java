package com.github.x3r.solaris.common.worldgen.dimension;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class IslandNoiseTest {

    static IslandNoise noise = new IslandNoise(RandomSource.create(), 16);

    @org.junit.Test
    public void createTexture() {
        int size = 512;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                image.setRGB(i, j, new Color((float) getValue(i, j), (float) getValue(i, j), (float) getValue(i, j)).getRGB());
            }
        }
        try {
            String name = "noise";
            ImageIO.write(image, "png", new FileOutputStream("src/test/resources/"+name+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double getValue(int x, int z) {
        return Mth.clamp(noise.getValue(x, z), 0, 1);
    }
}