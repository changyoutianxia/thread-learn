package ch.chang.thread.singleton;

/**
 * 存在问题就是在
 * singletonONe = new SingletonONe();
 * 返回后，还有一些构建中的时候，然后另外一个程序就调用了这个singletonONe
 * 导致发生NullPointerException
 */
public class SingletonONe {
    private static SingletonONe singletonONe;

    private SingletonONe() {

    }

    public static SingletonONe getInstance() {
        if (null == singletonONe) {
            synchronized (SingletonONe.class) {
                if (null == singletonONe) {
                    singletonONe = new SingletonONe();
                }
            }
        }
        return singletonONe;
    }

    public static void main(String[] args) {
        String a = null;
        System.out.println(a.length());
    }

}
