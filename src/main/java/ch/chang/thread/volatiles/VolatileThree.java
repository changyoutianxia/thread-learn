package ch.chang.thread.volatiles;

/**
 * volatile
 * 可以保证可见，有序（禁止了重排序），不能保证原子性
 *
 *  i=0
 *  i++
 *      temp = i
 *      i=temp+1
 *      i=1
 * 出现两个一样
 *
 *  如果是写操作，会将其他的cpu 缓存失效
 *
 *  一般用于标志
 */
public class VolatileThree {
    private static final int max = 100;
    private static volatile int currentIndex = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int current = currentIndex;
            while (current < max) {
                if (current != currentIndex) {
                    System.out.println("t1:" + ++currentIndex);
                    current = currentIndex;
                }
            }
        }, "t1").start();
        new Thread(() -> {
            int current = currentIndex;
            while (currentIndex < max) {
                current++;
                currentIndex = current;
                System.out.println("t2:" + currentIndex);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t2").start();
    }
}
