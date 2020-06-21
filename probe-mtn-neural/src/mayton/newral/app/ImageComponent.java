package mayton.newral.app;

import javax.swing.*;
import java.awt.*;



public class ImageComponent extends JComponent {

  Image image;
  Dimension size;

  public ImageComponent(Image image)
  {
    assert(image!=null);
    this.image = image;
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(image, 0);
    try {
      mt.waitForAll(  );
    }
    catch (InterruptedException e) {
    };
    size = new Dimension (image.getWidth(null),
                          image.getHeight(null));
    setSize(size);
  }

  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
  }

  public Dimension getPreferredSize(  ) {
    return size;
  }
}
