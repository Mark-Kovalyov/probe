package mayton.probe.eclipse.rdf;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Sofar sofar = new Sofar("row", 10);

        Random r = new Random();

        for(int i=0; i < 30 ; i++) {
            try {
                Thread.sleep((int) Math.abs(1000.0 * 3.0 * r.nextGaussian()));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sofar.update(i);
            System.out.println(sofar.getSnapshot().toString());
        }


    }

}
