package ch.chang.thread.singleton;

/**
 * 枚举类 ，只会加载一次
 */
public class SingletonFour {

    private SingletonFour() {

    }

    private enum singleton {
        INSTANCE;
        private final SingletonFour singletonFour;

        singleton() {
            singletonFour = new SingletonFour();
        }

        public SingletonFour getInstance() {
            return singletonFour;
        }
    }

    public static SingletonFour getInstance() {
        return singleton.INSTANCE.getInstance();
    }

}
