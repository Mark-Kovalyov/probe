package mayton.probe;

public class Main {

    public static void main(String[] args) {
        new Thread(new DockerRunner()).start();
    }

}
