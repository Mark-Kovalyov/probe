package mayton;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Random;

public class Test {

    static Logger logger = org.apache.log4j.Logger.getLogger(Test.class);

    public Test() throws Exception {
        final FileRing r = new FileRing("out/my.ring", 16_384, 40);

        Thread procuder = new Thread(() -> {
            Random random = new Random();
            try {
                for (int i = 0; i < 20_000; i++) {
                    r.add(Integer.valueOf(i));
                }
                logger.info("Successfully wrote 10_000 Integer objects");
            } catch (Exception e) {
                logger.error(e);
            }
        });

        procuder.start();

        procuder.join();

        Thread consumer = new Thread(() -> {
            try {
                int sum = 0;
                for (int i = 0; i < 20_000; i++) {
                    Serializable object = r.poll();
                    sum += (Integer) object;
                }
                logger.info("Successfully read 20_000 Integer objects with sum = " + sum);
            } catch (Exception e) {
                logger.error("Exception during consumer loop", e);
            }
        });

        consumer.start();

        consumer.join();
    }

    public static void main(String argv[]) throws Exception {

        new Test();

    }

}
