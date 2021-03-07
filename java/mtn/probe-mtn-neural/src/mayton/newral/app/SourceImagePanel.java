/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mayton.newral.app;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author mayton
 */
public class SourceImagePanel extends JPanel
{
    NNCodekMain NNCodekMainObj;

    public SourceImagePanel(NNCodekMain NNCodekMainObj)
    {
        this.NNCodekMainObj=NNCodekMainObj;
        this.setBackground(Color.GRAY);
        this.setForeground(Color.GRAY);
        //Component comp=new ImageComponent(NNCodekMainObj.sourceImage);
        //comp.setLocation(0,0);
        //this.add(comp);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(NNCodekMainObj.sourceImage,0,0,null);
    }
}
