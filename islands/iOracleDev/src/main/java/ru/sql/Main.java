package ru.sql;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //   1 /\ 2
        //  0 |  | 3
        //   5 \/ 4

        //         / \ / \ / \                    / \ / \ / \
        //        | 0 | 1 | 2 |                  | 1 | 4 | 3 |
        //       / \ / \ / \ / \                / \ / \ / \ / \
        //      | 3 | 4 | 5 | 6 |              | 1 | 5 | 1 | 5 |
        //     / \ / \ / \ / \ / \            / \ / \ / \ / \ / \
        //    | 7 | 8 | 9 | 10| 11|          | 2 | 3 | 2 | 4 | 6 |
        //     \ / \ / \ / \ / \ /            \ / \ / \ / \ / \ /
        //      | 12| 13| 14| 15|              | 2 | 6 | 4 | 2 |
        //       \ / \ / \ / \ /                \ / \ / \ / \ /
        //        | 16| 17| 18|                  | 3 | 1 | 2 |
        //         \ / \ / \ /                    \ / \ / \ /

        int[][] array = new int[][] {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 4, 3, 0},
                {0, 0, 1, 5, 1, 5, 0},
                {0, 2, 3, 2, 4, 6, 0},
                {0, 2, 6, 4, 2, 0, 0},
                {0, 3, 1, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };

        // массив шестиугольников
        Hexagon[][] arrayHexagons = new Hexagon[array.length][array[0].length];
        // список шестиугольников
        List<Hexagon> listOfHexagons = new ArrayList<>(20);

        // заполняем массив и список, ссылки в массиве и списке на одни и те же шестиугольники
        for (int i = 0; i < array.length; i++ ) {
            for (int k = 0; k < array[i].length; k++) {
                if (array[i][k] > 0) {
                    arrayHexagons[i][k] = new Hexagon(array[i][k]);
                    listOfHexagons.add(arrayHexagons[i][k]);
                } else {
                    arrayHexagons[i][k] = null;
                }
            }
        }

        // записываем связи шестиугольников друг с другом
        for (int i = 1; i < array.length - 1; i++ ) {
            for (int k = 1; k < array[i].length - 1; k++) {
                if (array[i][k] > 0) {
                    arrayHexagons[i][k].setHexagonSides(new Hexagon[] {
                            arrayHexagons[i][k-1], arrayHexagons[i-1][k], arrayHexagons[i-1][k+1],
                            arrayHexagons[i][k+1], arrayHexagons[i+1][k], arrayHexagons[i+1][k-1] });
                }
            }
        }

        System.out.println(listOfHexagons);
        System.out.println("====================================");
        for (Hexagon[] h1: arrayHexagons) {
            for (Hexagon h2: h1) {
                if (h2 != null) {
                    System.out.print(h2 + " - ");
                    for (int i = 0; i < Hexagon.NUMBER_OF_SIDES; i++) {
                        System.out.print(h2.getHexagonSide(i) + ((i+1 < Hexagon.NUMBER_OF_SIDES) ? ", " : ""));
                    }
                    System.out.println();
                }
            }
        }
    }
}