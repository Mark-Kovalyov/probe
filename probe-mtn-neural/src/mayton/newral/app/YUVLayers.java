package mayton.newral.app;

//import mayton.image.Raster;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class YUVLayers extends JPanel {

    NNCodekMain NNCodekMainObj;
    BufferedImage bi;
    int X,Y;
    double Yavg;
    double Yavgsq;


    public void update()
    {
        X=NNCodekMainObj.sourceImage.getWidth(null);
        Y=NNCodekMainObj.sourceImage.getHeight(null);
        bi = new BufferedImage(X*2, Y*2, BufferedImage.TYPE_INT_RGB);
        Graphics2D g=bi.createGraphics();

        //NNCodekMainObj.sourceImage.getGraphics().

        Random r=new Random();

        for(int x=0;x<X;x++)
        {
            for(int y=0;y<Y;y++)
            {
                bi.setRGB(x,y,NNCodekMainObj.sourceImage.getRGB(x,y));

                /*
                int Yvalue=(int)(255.0*Raster.getYPixelDouble(NNCodekMainObj.sourceImage.getRGB(x,y)));
                Yavg+=Yvalue;
                Yavgsq+=Yvalue*Yvalue;

                bi.setRGB(x+X,y,Raster.getPixel(Yvalue,Yvalue,Yvalue));
                int Uvalue=(int)(255.0*Raster.getUPixelDouble(NNCodekMainObj.sourceImage.getRGB(x,y)));

                bi.setRGB(x,y+Y,Raster.getPixel(Uvalue,Uvalue,Uvalue));
                int Vvalue=(int)(255.0*Raster.getVPixelDouble(NNCodekMainObj.sourceImage.getRGB(x,y)));

                bi.setRGB(x+X,y+Y,Raster.getPixel(Vvalue,Vvalue,Vvalue));
                */

            }
        }
        Yavg/=(X*Y);
        Yavgsq-=Yavg*Yavg;
        Yavgsq=Math.sqrt(Yavgsq);
    }

    public YUVLayers(NNCodekMain NNCodekMainObj)
    {
        this.NNCodekMainObj=NNCodekMainObj;
        update();
    }

    public void drawShadowString(Graphics g,int x,int y,String s)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        
        g.setColor(Color.BLACK);
        g.drawString(s,x,y);
        g.setColor(Color.green);
        g.drawString(s,x-1,y-1);
    }

    @Override
    public void paint(Graphics g) {
        
        g.drawImage(bi,0,0,null);

        drawShadowString(g,16,16,"RGB Layer");
        drawShadowString(g,16,32,"Average        = "+String.format("%4.4f",Yavg));
        drawShadowString(g,16,48,"sqrt(Disperse) = "+String.format("%4.4f",Yavgsq));

        drawShadowString(g,16+X,16,"Y Layer");
        drawShadowString(g,16+X,16+Y,"V Layer");
        drawShadowString(g,16,16+Y,"U Layer");

    }

}
