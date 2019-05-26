package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 内部封装了一个
 * private volatile int value;
 *
 * 1：true
 * 0:false
 */
public class AtomicBooleanDemo {
    @Test
    public void create() {
        AtomicBoolean atomicBooleanInit = new AtomicBoolean();
        System.out.println(atomicBooleanInit.get());
        AtomicBoolean atomicBooleanInitConstrucator = new AtomicBoolean(true);
        System.out.println(atomicBooleanInitConstrucator.get());
    }
}
