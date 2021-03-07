package mayton.algo.island;

import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.NotThreadSafe;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@NotThreadSafe
public class RasterCellGraph {

    public BufferedImage heightMap;
    public BufferedImage waterMap;

    public RasterCellGraph(@NotNull BufferedImage heightMap, @NotNull BufferedImage water) {
        this.heightMap = heightMap;
        this.waterMap = water;
    }

    public List<ICell> getAllCells() {
        List<ICell> cells = new ArrayList<>();
        for(int x = 0; x < heightMap.getWidth(); x++) {
            for(int y = 0;y< heightMap.getHeight(); y++ ) {

            }
        }
        return cells;
    }

}
