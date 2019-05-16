package ch.chang.classLoader;

public class ClinitDemo {
    public static void main(String[] args) {
        /**
         * 第二个将不能进入，虚拟机保证了线程安全，clinit 执行静态代码块
         */
        new Thread(() -> {
            new Student();
        }).start();
        new Thread(() -> {
            new Student();
        }).start();
    }
}

class Student {
    public static final Boolean b = new Boolean(true);

    static {
        System.out.println(Thread.currentThread().getName() + " before");
        while (b) {

        }
        System.out.println(Thread.currentThread().getName() + " end");

    }

}

