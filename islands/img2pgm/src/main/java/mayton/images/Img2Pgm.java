package mayton.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Img2Pgm {

    public static final double GK = 0.587;
    public static final double BK = 0.114;
    public static final double RK = 0.299;

    public static final int MAX_SYMBOLS_PER_LINE = 70;

    public static final int MAX_PIXELS_PER_LINE = MAX_SYMBOLS_PER_LINE / "XXX".length();

    public static double getYPixelDouble(int color) {
        return (GK * ((color & 0x00FF00) >> 8) + BK * (color & 0x0000FF) + RK * ((color & 0xFF0000) >> 16)) / 255.0;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(args[0]));
        PrintWriter pgm = new PrintWriter(new FileWriter(args[1]));
        pgm.printf("P5\n");
        pgm.printf("# Generated with java Img2Pgm converter\n");
        pgm.printf("%d %d\n", image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getWidth(); y++) {
            for (int x = 0; x < image.getHeight(); x++) {
                int color = image.getRGB(x, y);
                int gray = (int) (255.0 * getYPixelDouble(color));
                pgm.println(gray);
            }
        }
        pgm.close();
    }

}
