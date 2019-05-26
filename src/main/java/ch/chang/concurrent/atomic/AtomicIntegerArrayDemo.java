package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 其他同AtomicInteger 一致，
 * 区别就是这个是数组
 * 还存在AtomicLongArray ,,,,
 */
public class AtomicIntegerArrayDemo {
    @Test
    public void create() {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        boolean b = atomicIntegerArray.compareAndSet(0, 0, 2);
        System.out.println(b);
    }
}
