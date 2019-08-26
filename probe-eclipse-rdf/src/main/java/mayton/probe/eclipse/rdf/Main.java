package mayton.probe.eclipse.rdf;

import mayton.lib.SofarTracker;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        SofarTracker sofar = SofarTracker.createUnitLikeTracker("row", 10);

        Random r = new Random();

        for(int i=0; i < 10 ; i++) {
            try {
                Thread.sleep((int) Math.abs(1000.0 * 2.0 * r.nextGaussian()));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sofar.update(i);
            System.out.println(sofar.toString());
        }

        SofarTracker sofarBytes = SofarTracker.createFileSizeTracker(10_000_500);

        int i = 0;
        while(i < 10_000_500) {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sofarBytes.update(i);
            i += r.nextInt(100_000);
            System.out.println(sofarBytes.toString());
        }


    }

}
