package mayton.algo;

import mayton.image.standard.Resolutions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        Properties props=new Properties();
        props.load(new FileInputStream("sensitive.properties"));
        int width = Resolutions.FULL_HD.x;
        int height = Resolutions.FULL_HD.y;
        int border = 32;
        int gridSize = 50;
        int gridStep = 16;
        int radius = 5;
        String dest = props.getProperty("dest");
        for(int i = 0 ; i< 1; i++) {
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
            graphics2D.setColor(Color.BLACK);
            graphics2D.setBackground(Color.BLACK);
            graphics2D.fillRect(0,0, width, height);
            graphics2D.setColor(Color.white);
            graphics2D.setBackground(Color.GREEN);
            for(int x = 0; x < gridSize ; x ++)  {
                for(int y = 0; y < gridSize ; y ++) {
                    graphics2D.fillOval(x * gridStep, y * gridStep, radius, radius);
                }
            }
            ImageIO.write(bufferedImage, "PNG", new FileOutputStream(dest + String.format("/%08d.png", i)));
        }
    }

}
