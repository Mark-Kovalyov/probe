package mayton.probe;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProbeCompletableFuture {

    public static void main(String[] args) {
        ExecutorService ryzenThreads = Executors.newFixedThreadPool(12);
        ExecutorService diskThreads = Executors.newFixedThreadPool(3);
        for(int i = 0;i < 100;i++) {
            CompletableFuture.supplyAsync(() -> getOrder(), ryzenThreads)
                    .thenApplyAsync(order -> enrichOrder(order), ryzenThreads)
                    .exceptionally(ex -> new FailOrder())
                    .thenApplyAsync(order -> copyOrder(order), diskThreads)
                    .thenAccept(order -> sendEmail(order));
        }
    }

    private static void sendEmail(Object order) {

    }

    private static Object copyOrder(Object order) {
        return null;
    }

    private static Object enrichOrder(Object order) {
        return null;
    }

    private static <U> U getOrder() {
        return null;
    }


}
