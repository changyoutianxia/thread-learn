package ch.chang.thread.learn.pattern.countDown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * countdown
 * 就是 只有将coutDown 里面的值减小到0才可以进行 继续下面的任务
 * 感觉类似于join
 */
public class CountDownDisplay {
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        System.out.println("start");
        new Thread(() -> {

            try {
                Thread.sleep(random.nextInt(10000));
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {

            try {
                Thread.sleep(random.nextInt(10000));
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
        new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(10000));
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");

    }
}
