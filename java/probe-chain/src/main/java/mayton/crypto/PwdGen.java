package mayton.crypto;

import java.util.concurrent.BlockingQueue;

public class PwdGen implements Runnable {

    private BlockingQueue<String> queue;
    private String alphabet;
    private int max;

    private char[] buf;

    private void recursiveCall(int level) throws InterruptedException {
        if (level >= max) {
            queue.put(new String(buf));
        } else {
            for (int i = 0; i < alphabet.length(); i++) {
                buf[level] = alphabet.charAt(i);
                recursiveCall(level + 1);
            }
        }
    }

    public PwdGen(BlockingQueue<String> queue, String alphabet, int max) {
        this.queue = queue;
        this.alphabet = alphabet;
        this.max = max;
        this.buf = new char[max];
    }

    @Override
    public void run() {
        try {
            recursiveCall(0);
            // put poison pillow
            queue.put("\uFFFF");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
