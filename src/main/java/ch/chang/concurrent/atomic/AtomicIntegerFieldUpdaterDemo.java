package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 用于操作某一个对象中的 public volatile 变量
 * 同一个类可以是private protected
 */
public class AtomicIntegerFieldUpdaterDemo {
    @Test
    public void demo() {
        AtomicIntegerFieldUpdater<Book> bookAtomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Book.class, "use");
        Book book = new Book();
        bookAtomicIntegerFieldUpdater.compareAndSet(book, 0, 1);
    }

    @Test
    public void notExistField() {
        /**
         *   不存在的列
         *  java.lang.RuntimeException: java.lang.NoSuchFieldException: us
         */
        AtomicIntegerFieldUpdater<Book> bookAtomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Book.class, "us");
        Book book = new Book();
        bookAtomicIntegerFieldUpdater.compareAndSet(book, 0, 1);
    }

    private static class Book {
        volatile int use;
    }
}
