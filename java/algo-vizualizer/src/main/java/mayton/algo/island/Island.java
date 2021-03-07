package mayton.algo.island;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Island {

    public static void main(String[] args) throws IOException {
        BufferedImage height = ImageIO.read(new FileInputStream("src/main/resources/heightmap.jpeg"));
        int w = height.getWidth();
        int h = height.getHeight();
        System.out.printf("x = %d, y = %d\n", w, h);
        BufferedImage water = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        // Flood water
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                water.setRGB(x, y, 255);
            }
        }
        boolean changes = false;
        do {
            for (int y = 1; y < h - 1; y++) {
                for (int x = 1; x < w - 1; x++) {
                    // TODO
                }
            }
        } while(changes);
        ImageIO.write(water, "PNG", new FileOutputStream("src/main/resources/water.png"));
    }

}
