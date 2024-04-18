package com.github.x3r.solaris.common.worldgen.dimension;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import org.antlr.v4.runtime.misc.IntegerList;
import org.joml.Vector2i;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;

public class IslandNoiseTest {

    static IslandNoise noise = new IslandNoise(RandomSource.create(0), 128);

    @org.junit.Test
    public void createTexture() {
        int size = 2560;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_3BYTE_BGR);
        IntStream.range(0, size).parallel().forEach(i -> {
            IntStream.range(0, size).parallel().forEach(j -> {
                float value = (float) getValue(i, j);
                image.setRGB(i, j, FastColor.ARGB32.color(255, (int) (255 * value), (int) (255 * value), (int) (255 * value)));
            });
        });
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                float value = (float) getValue(i, j);
////                Vector2i island = noise.getIslandPos(i, j);
////                int c = (int) Math.round(2 * noise.getIslandValue(island.x, island.y));
////                int[] colors = {0xFF0000, 0x00FF00, 0x0000FF};
//                image.setRGB(i, j, FastColor.ARGB32.color(255, (int) (255 * value), (int) (255 * value), (int) (255 * value)));
//            }
//        }
        try {
            String name = "noise";
            ImageIO.write(image, "png", new FileOutputStream("src/test/resources/"+name+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double getValue(int x, int z) {
        return noise.getValue(x, z);
    }
}