package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子的Integer
 */
public class AtomicIntegerDemo {
    @Test
    public void create() {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger);
        AtomicInteger atomicIntegerInit = new AtomicInteger(1);
        System.out.println(atomicIntegerInit);
    }

    @Test
    public void set() {
        AtomicInteger atomicIntegerInit = new AtomicInteger(1);
        atomicIntegerInit.lazySet(5);
        System.out.println(atomicIntegerInit.get());
        boolean b = atomicIntegerInit.compareAndSet(5, 6);
        System.out.println("compareAndSet result:" + b + "," + atomicIntegerInit.get());
    }
}
