package ch.chang.concurrent.utils;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PhaserDemo {
    private static Random random = new Random(System.currentTimeMillis());

    @Test
    public void demo() {
        Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).forEach(integer -> new MyTask(phaser));
        phaser.register();
        phaser.arriveAndAwaitAdvance();
    }

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
}
