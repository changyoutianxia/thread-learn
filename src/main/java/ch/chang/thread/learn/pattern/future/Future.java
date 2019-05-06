package ch.chang.thread.learn.pattern.future;


public interface Future<T> {

    T get() throws InterruptedException;

}