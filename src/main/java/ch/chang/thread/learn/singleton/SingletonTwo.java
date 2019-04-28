package ch.chang.thread.learn.singleton;

/**
 * volatiles 会保证执行在读取的时候写已经完成
 */
public class SingletonTwo {
    private static volatile SingletonTwo singletonTwo;

    private SingletonTwo() {

    }

    public static SingletonTwo getInstance() {
        if (null == singletonTwo) {
            synchronized (SingletonTwo.class) {
                if (null == singletonTwo) {
                    singletonTwo = new SingletonTwo();
                }
            }
        }
        return singletonTwo;
    }

    public static void main(String[] args) {
        String a = null;
        System.out.println(a.length());
    }

}
