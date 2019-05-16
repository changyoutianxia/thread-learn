package ch.chang.thread.volatiles;

/**
 * 没有使用volatile关键字，
 * thread1 的更新并不会引起thread 1的发现
 */
public class VolatileTwo {
    private static final int max = 100;
    private static volatile int currentIndex = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int current = currentIndex;
            while (current < max) {
                if (current != currentIndex) {
                    System.out.println("p currentIndex:" + currentIndex);
                    current = currentIndex;
                }
            }
        }, "t1").start();
        new Thread(() -> {
            int current = currentIndex;
            while (currentIndex < max) {
                current++;
                currentIndex = current;
                System.out.println("update:" + currentIndex);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t2").start();
    }
}
