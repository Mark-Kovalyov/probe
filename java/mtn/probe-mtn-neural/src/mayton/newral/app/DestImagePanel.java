/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mayton.newral.app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author mayton
 */
public class DestImagePanel extends JPanel
{
    NNCodekMain NNCodekMainObj;

    BufferedImage bi;

    public DestImagePanel(NNCodekMain NNCodekMainObj)
    {
        this.NNCodekMainObj=NNCodekMainObj;
        int X=NNCodekMainObj.sourceImage.getWidth(null);
        int Y=NNCodekMainObj.sourceImage.getHeight(null);
        bi = new BufferedImage(X, Y, BufferedImage.TYPE_INT_RGB);
        Graphics2D g=bi.createGraphics();
        for(int x=0;x<X;x++)
        {
            for(int y=0;y<Y;y++)
            {
                bi.setRGB(x,y,0xFF000000|(x%256)<<8|(y%256)<<16);
            }
        }

    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(bi,0,0,null);
    }
}