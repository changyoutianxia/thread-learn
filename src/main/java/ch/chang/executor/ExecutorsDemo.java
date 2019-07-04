package ch.chang.executor;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorsDemo {
    /**
     * public ThreadPoolExecutor(int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler) {
     */
    @Test
    public void params() {
        int corePoolSize = 1;
        int maximumPoolSize = 2;
        int keepAliveTime = 5;
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, arrayBlockingQueue, r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /**提供submitSize> maximumPoolSize+BlockingQueue.size
         * 采用拒绝策略的时候机会抛出异常
         */
        /*threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        printInfo(threadPoolExecutor);
    }

    /**
     * 该方式会不断的创建线程，60s回收一次
     */
    @Test
    public void newCachedThreadPool() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        IntStream.rangeClosed(0, 20).forEach(i ->
                executorService.submit(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " start");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                })
        );
        printInfo(executorService);

    }

    /**
     *
     * 固定大小 max=min=10
     * queue.size =Integer.max
     */
    @Test
    public void newFixedThreadPool() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        IntStream.rangeClosed(0, 20).forEach(i ->
                executorService.submit(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " start");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                })
        );
        printInfo(executorService);
    }

    /**
     * 只有一个线程
     * 类型不是ThreadPoolExecutor
     * 仅仅暴露了ExecutorService
     */
    @Test
    public void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.rangeClosed(0, 20).forEach(i ->
                executorService.submit(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " start");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                })
        );

    }

    private void printInfo(ThreadPoolExecutor executorService) {
        while (true) {
            System.out.println("isTerminated:" + executorService.isTerminated());
            //正在运行的数量
            System.out.println("activeCount:" + executorService.getActiveCount());
            //待完成数量
            System.out.println("queueSize:" + executorService.getQueue().size());
            //完成数量
            System.out.println("completedTaskCount:" + executorService.getCompletedTaskCount());
            //任务数量
            System.out.println("taskCount:" + executorService.getTaskCount());
            //保留的线程数量
            System.out.println("poolSize:" + executorService.getPoolSize());
            System.out.println("==========================================");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
