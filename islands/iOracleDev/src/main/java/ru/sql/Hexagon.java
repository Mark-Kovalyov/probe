package ru.sql;

public class Hexagon {

    public static final int NUMBER_OF_SIDES = 6;
    private int height;
    private String status;

    // индексы ребер
    //   1 /\ 2
    //  0 |  | 3
    //   5 \/ 4
    private Hexagon[] hexagonSides = new Hexagon[NUMBER_OF_SIDES];

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHexagonSides(Hexagon[] hexagonSides) {
        this.hexagonSides = hexagonSides;
    }

    public Hexagon getHexagonSide(int side) {
        return hexagonSides[side];
    }

    public void setHexagonSide(int side, Hexagon hexagon) {
        this.hexagonSides[side] = hexagon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Hexagon(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return Integer.toString(this.height);
    }
}