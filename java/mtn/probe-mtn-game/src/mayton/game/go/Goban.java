package mayton.game.go;

import java.awt.Graphics;
import javax.swing.JFrame;


public class Goban extends JFrame
{
    Thread t;

    @Override
    public void paint(Graphics grphcs) {

    }

    public Goban()
    {
        super("Goban 1.0");
        this.setSize(320, 200);
        this.setVisible(true);
        //t=new Thread(this);
        //t.start();
    }

    public static void main()
    {
        
    }

}
