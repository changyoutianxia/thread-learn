package ch.chang.thread.pattern.future;


public interface Future<T> {

    T get() throws InterruptedException;

}