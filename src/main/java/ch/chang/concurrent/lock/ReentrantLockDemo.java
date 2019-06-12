package ch.chang.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
    }

    /**
     * 该方式获取锁，不能被打断
     */
    public static void testLock() {
        try {
            lock.lock();
            while (true) {

            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * 可被中断的锁
     */
    public static void testLockInterruptibly() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "- this thread has lock");
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 尝试获取，如果获取不到就直接返回
     */
    public static void testTryLock() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "- this thread has lock");
                while (true) {

                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "- this thread get failed lock");
        }
    }

    /**
     * 尝试获取，如果获取不到就直接返回，可以打断的
     */
    public static void testTryLockWaitTime() {
        try {
            lock.tryLock(1000, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "- this thread has lock");
            while (true) {
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取当前线程持有该锁的个数
     */
    public static void testHolderCount() {
        try {
            int holdCount = lock.getHoldCount();
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "- this thread has lock");
            while (true) {
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 当前线程是否持有该锁
     * isHeldByCurrentThread
     */
    public static void testisHeldByCurrentThread() {
        try {
            boolean heldByCurrentThread = lock.isHeldByCurrentThread();
            System.out.println("current lock status:" + heldByCurrentThread);
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "- this thread has lock");
            while (true) {
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 查看该锁是否已经被使用
     */
    public static void testisLocked() {
        try {
            boolean isLocked = lock.isLocked();
            System.out.println("current lock status:" + isLocked);
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "- this thread has lock");
            while (true) {
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 查看等待队列
     */
    public static void testHasQueuedThreads() {
        try {
            boolean b = lock.hasQueuedThreads();
            int queueLength = lock.getQueueLength();
            //是否包含某个线程
//            lock.hasQueuedThread(Thread);
            System.out.println("wait queue status:" + b);
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "- this thread has lock");
            while (true) {
            }
        } finally {
            lock.unlock();
        }
    }
}
