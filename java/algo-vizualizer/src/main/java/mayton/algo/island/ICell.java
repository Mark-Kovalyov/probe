package mayton.algo.island;

public interface ICell {

    int getHeight();
    int getWaterLine();
    Iterable<ICell> neighbours();

}
