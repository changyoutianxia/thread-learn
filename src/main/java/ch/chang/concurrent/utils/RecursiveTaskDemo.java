package ch.chang.concurrent.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分而治之　有结果
 */
public class RecursiveTaskDemo {
    private static final Integer maxLength = 10;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new RecursiveTaskAdd(0,10000 ));
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class RecursiveTaskAdd extends RecursiveTask<Integer> {
        private final int max;
        private final int min;

        private RecursiveTaskAdd(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        protected Integer compute() {
            if (max - min <= maxLength) {
                int temp = 0;
                for (int i = min; i <= max; i++) {
                    temp += i;
                }
                return temp;
            }
            RecursiveTaskAdd leftFork = new RecursiveTaskAdd(min, (max + min) / 2);

            RecursiveTaskAdd rightFork = new RecursiveTaskAdd((max + min) / 2 + 1, max);
            leftFork.fork();
            rightFork.fork();
            return leftFork.join() + rightFork.join();
        }
    }
}
