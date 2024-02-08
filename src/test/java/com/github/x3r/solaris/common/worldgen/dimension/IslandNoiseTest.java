package com.github.x3r.solaris.common.worldgen.dimension;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.joml.Vector2i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class IslandNoiseTest {

    static IslandNoise noise = new IslandNoise(RandomSource.create(0), 128);

    @org.junit.Test
    public void createTexture() {
        int size = 1280;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                float value = (float) getValue(i, j);
                Vector2i island = noise.getIslandPos(i, j);
                int c = (int) Math.round(2 * noise.getIslandValue(island.x, island.y));
                int[] colors = {0xFF0000, 0x00FF00, 0x0000FF};
                image.setRGB(i, j, value == 1 ? colors[c] : 0);
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
        return noise.getValue(x, z) > 0.6 ? 1 : 0;
    }
}