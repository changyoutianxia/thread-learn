package ch.chang.concurrent.utils;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 用于避免1:99导致１很少被调用问题
 */
public class StampedLockDemo {
    private static final StampedLock lock = new StampedLock();
    private static final LinkedList<Long> chain = new LinkedList<>();

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable producer = new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    producer();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable customer = new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    customer();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(producer);
        executorService.submit(producer);
        executorService.submit(producer);
        executorService.submit(producer);
        executorService.submit(producer);
        executorService.submit(producer);

        executorService.submit(producer);
        executorService.submit(customer);
        executorService.submit(customer);
        executorService.submit(customer);


    }


    private static void producer() {
        long stamp = -1l;
        try {
            stamp = lock.writeLock();
            long pushData = System.currentTimeMillis();
            chain.addLast(pushData);

            System.out.println(Thread.currentThread().getName() + " P-> values: " + pushData);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    /*    private static void customer() {
            long stamp = -1l;

            try {
                stamp = lock.readLock();
                int size = chain.size();
                System.out.println(Thread.currentThread().getName() + " C-> size: " + size);

            }finally {
                lock.unlockRead(stamp);
            }
        }*/

    /**
     * 可以尽可能的获取到锁
     */
    private static void customer() {
        long stamp = -1l;

        //尝试获取

        stamp = lock.tryOptimisticRead();

        if (lock.validate(stamp)) {
            try {
                stamp = lock.readLock();
                int size = chain.size();
                System.out.println(Thread.currentThread().getName() + " -> values: " + size);
            } finally {
                lock.unlockRead(stamp);
            }
        }

    }

}
