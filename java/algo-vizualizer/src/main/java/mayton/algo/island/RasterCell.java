package mayton.algo.island;

import javax.annotation.concurrent.NotThreadSafe;
import java.awt.image.BufferedImage;

@NotThreadSafe
public class RasterCell implements ICell {

    public BufferedImage height;
    public BufferedImage width;

    private RasterCell() {

    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWaterLine() {
        return 0;
    }

    @Override
    public Iterable<ICell> neighbours() {
        return null;
    }
}
