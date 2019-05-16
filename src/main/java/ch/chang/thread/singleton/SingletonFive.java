package ch.chang.thread.singleton;

/**
 * 恶汉加载模式
 */
public class SingletonFive {

    private static final SingletonFive singletonFive = new SingletonFive();

    private SingletonFive() {

    }


    public static SingletonFive getInstance() {

        return singletonFive;
    }

}
