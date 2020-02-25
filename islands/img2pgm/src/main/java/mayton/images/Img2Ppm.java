package mayton.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Img2Ppm {

    public static final int MAX_SYMBOLS_PER_LINE = 70;

    public static int getRPixel(int color) {
        return (0x00FF0000&color)>>16;
    }

    public static int getGPixel(int color) {
        return (0x0000FF00&color)>>8;
    }

    public static int getBPixel(int color) {
        return (0xFF&color);
    }


    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(args[0]));
        PrintWriter pgm = new PrintWriter(new FileWriter(args[1]));
        pgm.printf("P6\n");
        pgm.printf("# Generated with java Img2Ppm converter\n");
        pgm.printf("%d %d\n", image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getWidth(); y++) {
            for (int x = 0; x < image.getHeight(); x++) {
                int color = image.getRGB(x, y);
                pgm.print(getRPixel(color));
                pgm.print(" ");
                pgm.print(getGPixel(color));
                pgm.print(" ");
                pgm.println(getBPixel(color));
            }
        }
        pgm.close();
    }

}
