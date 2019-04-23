package ch.chang.thread.learn.singleton;

/**
 * 使用内部类，做到唯一
 */
public class SingletonThree {
    private SingletonThree() {

    }

    private static class InstanceHolder {
        private final static SingletonThree singletonThree = new SingletonThree();
    }

    public static SingletonThree getInstance() {
        return InstanceHolder.singletonThree;
    }
}
