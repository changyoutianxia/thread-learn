package ch.chang.concurrent.utils;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Phaser
 *      可以实现复用
 *      countDownLatch功能
 *      每次循环结束后会变化当前的批次phase
 *      还有其他方法没有实践
 *
 */
public class PhaserDemo {
    private static Random random = new Random(System.currentTimeMillis());

    static class MyTask extends Thread {
        private Phaser phaser;

        public MyTask(Phaser phaser) {
            this.phaser = phaser;
            phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "start");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "end");
            phaser.arriveAndAwaitAdvance();
        }
    }

    static class CycleTask extends Thread {
        private Phaser phaser;
        private int id;

        public CycleTask(Phaser phaser, int id) {
            this.phaser = phaser;
            this.id = id;
            start();
        }

        @Override
        public void run() {
            try {

                System.out.println(Thread.currentThread().getName() + ", one\t" + id + "start");
                TimeUnit.SECONDS.sleep(random.nextInt(2));
                System.out.println(Thread.currentThread().getName() + ", one\t" + id + "end");
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + ", two\t" + id + "start");
                TimeUnit.SECONDS.sleep(random.nextInt(2));
                System.out.println(Thread.currentThread().getName() + ", two\t" + id + "end");
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void demo() {
        Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).forEach(integer -> new MyTask(phaser));
        phaser.register();
        phaser.arriveAndAwaitAdvance();
    }

    @Test
    public void cycle() throws InterruptedException {
        final Phaser phaser = new Phaser(5);
        IntStream.rangeClosed(1, 5).forEach(integer -> new CycleTask(phaser, integer));
        Thread.currentThread().join();
    }

}
