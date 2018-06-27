package mayton.camel;


import static java.lang.System.out;

public class probesize {

    public static void main(String[] args) {

        Vector v = new Vector(0.0, 0.0, 0.0);

        out.println("Vector size = " + ObjectSizeFetcher.getObjectSize(v));

    }


}
