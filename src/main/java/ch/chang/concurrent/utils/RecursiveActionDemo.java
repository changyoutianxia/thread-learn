package ch.chang.concurrent.utils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 没有返回值的计算
 */
public class RecursiveActionDemo {
    private static final Integer maxLength = 3;
    private static final AtomicInteger total = new AtomicInteger(0);

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new RecursiveActionAdd(0, 10));
        try {
            forkJoinPool.awaitTermination(15, TimeUnit.SECONDS);
            System.out.println(total);
            ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class RecursiveActionAdd extends RecursiveAction {
        private final int max;
        private final int min;

        private RecursiveActionAdd(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        protected void compute() {
            if (max - min <= maxLength) {
                int temp = 0;
                for (int i = min; i <= max; i++) {
                    temp += i;
                }
                total.getAndAdd(temp);
                return ;
            }
            RecursiveActionAdd leftFork = new RecursiveActionAdd(min, (max + min) / 2);
            RecursiveActionAdd rightFork = new RecursiveActionAdd((max + min) / 2 + 1, max);

            leftFork.fork();
            rightFork.fork();
        }
    }
}
