package mayton.probe.simpleq;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Timer timer = new Timer("Timer-01");

        final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        final Random random = new Random();

        final AtomicLong atomicLong = new AtomicLong();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int r = random.nextInt();
                atomicLong.incrementAndGet();
                if (queue.offer(valueOf(r))) {
                    System.out.printf("TimerTask::offered %s \n", valueOf(r));
                } else {
                    System.out.printf("TimerTask::queue is full\n");
                }
            }
        }, 1000, 1000);

        Thread.sleep(15 * 1000);

        new Thread(() -> {
            try {
                while (true) {
                    String res = queue.poll(300, MILLISECONDS); // Receivs null after 300ms
                    if (res == null) throw new InterruptedException();
                    //String res = queue.take(); // Blocking
                    System.out.printf("Thread::received %s\n", res);
                    Thread.sleep(50);
                    System.out.printf("Thread::waits 50ms\n");
                }
            } catch (InterruptedException e) {
                System.out.printf("Thread::interrupted " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();

        synchronized (Main.class) {
            Main.class.wait();
        }


    }

}
