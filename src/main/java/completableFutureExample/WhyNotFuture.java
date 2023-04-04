package completableFutureExample;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WhyNotFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service=Executors.newFixedThreadPool(10);

        Future<List<Integer>> future = service.submit(() -> {
            System.out.println("Thread"+Thread.currentThread().getName());
            System.out.println(10/0);
            return Arrays.asList(1, 2, 3, 4);
        });

        List<Integer> list = future.get();
        System.out.println(list);
    }

    private static void delay(int min) {
        try {
            TimeUnit.MINUTES.sleep(min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
