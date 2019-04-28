package ch.chang.thread.learn.volatiles;

/**
 * 没有使用volatile关键字，
 * 如果thread2 不加睡眠 会发现thread 1 只会获取到部分数据
 * 如果thread2 加睡眠 会发现thread 1 只能获取到第一次初始值
 * thread2 的更新并不会引起thread 1的发现
 */
public class VolatileOne {
    private static final int max = 100;
    private static int currentIndex = 0;

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
