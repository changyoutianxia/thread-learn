package ch.chang.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCompletionServiceDemo {

    /**
     * 会阻塞
     */
    public static void take() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> integerExecutorCompletionService = new ExecutorCompletionService<>(executorService);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
            sleeps(3);
            return 1;
        }, () -> {
            sleeps(4);
            return 2;
        });
        callableList.stream().forEach(callable -> integerExecutorCompletionService.submit(callable));

        Future<Integer> take = null;
        try {
            while ((take = integerExecutorCompletionService.take()) != null) {
                System.out.println(take.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立刻返回
     *
     */
    public static void poll() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> integerExecutorCompletionService = new ExecutorCompletionService<>(executorService);

        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
            sleeps(3);
            return 1;
        }, () -> {
            sleeps(4);
            return 2;
        });
        callableList.stream().forEach(callable -> integerExecutorCompletionService.submit(callable));

        Future<Integer> take = null;
        try {
            take = integerExecutorCompletionService.poll();
            System.out.println(take.get());
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void sleeps(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        take();
        poll();
    }
}
