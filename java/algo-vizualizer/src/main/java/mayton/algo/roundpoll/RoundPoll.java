package mayton.algo.roundpoll;

import mayton.math.Vector;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Random;

import java.util.stream.IntStream;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class RoundPoll {

    public static boolean isPointInsideTriangle(double x0, double y0,double x1, double y1,double x2, double y2,double x3, double y3) {
        double p1 = (x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0);
        double p2 = (x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0);
        double p3 = (x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0);
        return  (p1 > 0.0 && p2 > 0.0 && p3 > 0.0) ||
                (p1 < 0.0 && p2 < 0.0 && p3 < 0.0);
    }

    public static void main(String[] args) {
        Random r = new Random();
        double TWOPI = 2.0 * Math.PI;
        int success = 0;
        int allProbes = 10000;
        for(int i = 0; i < allProbes; i++ ) {
            double a = TWOPI * r.nextDouble();
            double b = TWOPI * r.nextDouble();
            double c = TWOPI * r.nextDouble();
            double x1 = cos(a);
            double y1 = sin(a);
            double x2 = cos(b);
            double y2 = sin(b);
            double x3 = cos(c);
            double y3 = sin(c);
            boolean isInside = isPointInsideTriangle(0.0, 0.0, x1, y1, x2, y2, x3, y3);
            if (isInside) {
                success++;
            }
        }
        System.out.printf("Res = %.04f", (double) success / allProbes);
    }

}
