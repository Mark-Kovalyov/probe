package mayton.video;

import java.awt.image.BufferedImage;

public class FloatingPointRaster {

    public double[] r;
    public double[] g;
    public double[] b;

    public int width;
    public int height;
    public int size;

    public FloatingPointRaster(int width, int height) {
        this.width = width;
        this.height = height;
        size = width * height;
        r = new double[size];
        g = new double[size];
        b = new double[size];
    }

    private void cleanColorPlane(double[] x) {
        for (int i = 0; i < x.length; i++) {
            x[i] = 0.0f;
        }
    }

    private void cleanColorPlanes() {
        cleanColorPlane(r);
        cleanColorPlane(g);
        cleanColorPlane(b);
    }

    private static void addToColorPlanes(float v, BufferedImage bufferedImage) {

    }

}
