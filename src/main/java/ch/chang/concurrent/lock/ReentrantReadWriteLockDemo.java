package ch.chang.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    private static Long flag = 0L;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            write();
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            read();
        }).start();
        new Thread(() -> {
            read();
        }).start();
    }

    private static void read() {
        try {
            System.out.println(Thread.currentThread().getName() + "-read in");
            readLock.lock();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "-" + flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "-read out");
        }
    }

    private static void write() {
        try {
            System.out.println(Thread.currentThread().getName() + "-write in");
            writeLock.lock();
            flag = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-" + flag);
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "-write out");
        }
    }
}
