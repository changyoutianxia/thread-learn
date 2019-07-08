package ch.chang.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
//        isShutDown();
//        isTerminated();
//        allowCoreThreadTimeOut();
//        invokeAny();
//        invokeAnyAndTime();
//        invokeAll();
        invokeAllAndTime();
    }


    public static void isShutDown() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
    }

    public static void isTerminated() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        executorService.shutdown();
        System.out.println(executorService.isTerminating());
    }

    public static void allowCoreThreadTimeOut() {
        ThreadPoolExecutor threadPoolExecutor = (java.util.concurrent.ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        threadPoolExecutor.setKeepAliveTime(10, TimeUnit.SECONDS);
        //回收核心线程 ，必须设置回收时间
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        IntStream.range(0, 5).forEach(i -> {
            threadPoolExecutor.submit(
                    () -> {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        });
    }

    /**
     * 执行一个Callable 集合一个返回其他都结束
     */
    private static void invokeAny() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Integer integer = null;
        try {
            integer = executorService.invokeAny(
                    IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                        return i;
                    }).collect(Collectors.toList()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("=====================end=======================");
        System.out.println("result:" + integer);
    }

    private static void invokeAnyAndTime() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Integer integer = null;
        try {
            integer = executorService.invokeAny(
                    IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                        return i;
                    }).collect(Collectors.toList()), 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("=====================end=======================");
        System.out.println("result:" + integer);
    }


    /**
     * 返回future
     * 等待所有执行完毕
     */
    private static void invokeAll() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futures = null;
        try {
            futures = executorService.invokeAll(
                    IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                        return i;
                    }).collect(Collectors.toList()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====================end=======================");
        futures.stream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }

    private static void invokeAllAndTime() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futures = null;
        try {
            futures = executorService.invokeAll(
                    IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + "-" + i);
                        return i;
                    }).collect(Collectors.toList()), 2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====================end=======================");
        futures.stream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }

    /**
     * 执行前后
     */
    private static class MyThreadExector extends ThreadPoolExecutor {

        public MyThreadExector(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }
    }
}


