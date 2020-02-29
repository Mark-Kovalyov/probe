package mayton.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Img2Pgm {

    public static final double GK = 0.587;
    public static final double BK = 0.114;
    public static final double RK = 0.299;

    public static final int MAX_SYMBOLS_PER_LINE = 70;

    public static double getYPixelDouble(int color) {
        double res = (GK * ((color & 0x00FF00) >> 8) + BK * (color & 0x0000FF) + RK * ((color & 0xFF0000) >> 16)) / 255.0;
        //System.out.printf("getYPixelDouble( %08X ) = %f \n", color, res);
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(args[0]));
        PrintWriter pgm = new PrintWriter(new FileWriter(args[1]));
        System.out.printf("Converting from %s to %s\n", args[0], args[1]);
        System.out.printf("Color model          : %s\n", image.getColorModel().toString());
        System.out.printf("Size                 : %d x %d\n", image.getWidth(), image.getHeight());
        System.out.printf("Pixel size           : %d\n", image.getColorModel().getPixelSize());
        System.out.printf("Alpha premultiplied  : %s\n", image.getColorModel().isAlphaPremultiplied());
        System.out.printf("Num components       : %d\n", image.getColorModel().getNumComponents());
        System.out.printf("Num color components : %d\n", image.getColorModel().getNumColorComponents());
        System.out.printf("\n");
        pgm.printf("P2\n");
        pgm.printf("# Generated with java Img2Pgm converter\n");
        pgm.printf("%d %d\n", image.getWidth(), image.getHeight());
        pgm.printf("%d\n", 255);
        for (int y = 0; y < image.getWidth(); y++) {
            for (int x = 0; x < image.getHeight(); x++) {
                int color = image.getRGB(x, y);
                int gray = (int) (255.0 * getYPixelDouble(color));
                pgm.print(gray);
                pgm.print(" ");
            }
            pgm.println();
        }
        pgm.close();
    }

}
