package ch.chang.concurrent.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 相对于CountDownLatch
 * cyclicBarrier可以reset，
 * 在reset 之后恢复到初始状态
 * 可以有回调函数
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //支持一个runnable实现回调
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("all finished!");
            }
        });

        new Thread(() -> {

            try {
                Thread.sleep(5000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " finished!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }, "t1").start();

        new Thread(() -> {

            try {
                Thread.sleep(6000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " finished!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }, "t2").start();

        new Thread(() -> {

            try {
                Thread.sleep(2000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " finished!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }, "t3").start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("main");
    }
}
