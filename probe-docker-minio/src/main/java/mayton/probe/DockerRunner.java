package mayton.probe;

public class DockerRunner implements Runnable {

    @Override
    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder("");
        try {
            processBuilder.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
