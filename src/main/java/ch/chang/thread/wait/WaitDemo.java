package ch.chang.thread.wait;

public class WaitDemo {

    private static final Object LOCK = new Object();

    private static void work() {
        System.out.println("current thread will block");
        synchronized (LOCK) {
            System.out.println("current thread in");
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread out");
        }
    }

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                work();
            }
        }.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}
