package mayton.geo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author mayton
 * Swing компонент для отображения карты мира
 */
public class MapViewer extends JComponent {

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
    }

    Logger l=Logger.getLogger("MapViewer");

    int repaintCount=0;

    @Override
    public void repaint() {
        l.info("repaint "+repaintCount++);
    }

    @Override
    public void paint(Graphics g) {
        Rectangle rect = g.getClipBounds();
        for (int x = (int) rect.getMinX(); x < (int) rect.getMaxX(); x += 10) {

            g.drawLine(x, (int) rect.getMinY(), x, (int) rect.getMaxY());

        }

        for (int y = (int) rect.getMinY(); y < (int) rect.getMaxY(); y += 10) {

            g.drawLine((int) rect.getMinX(), y, (int) rect.getMaxX(),y);
        }
        
        g.drawRect(2+(int)rect.getMinX(),
                2+(int)rect.getMinY(),
                -4+(int)rect.getWidth(),
                -4+(int)rect.getHeight());
    }
}
