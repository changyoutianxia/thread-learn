package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 用于指定类型
 */
public class AtomicReferenceFieldUpdaterDemo {
    @Test
    public void demo() {
        AtomicReferenceFieldUpdater<Pencil, Integer> pencilIntegerAtomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Pencil.class, Integer.class, "use");
        Pencil pencil = new Pencil();
        boolean b = pencilIntegerAtomicReferenceFieldUpdater.compareAndSet(pencil, 0, 1);
        System.out.println(b);
    }

    /**
     * java.lang.ClassCastException
     */
    @Test
    public void fieldTypeInvalid() {
        AtomicReferenceFieldUpdater<Pencil, Long> pencilIntegerAtomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Pencil.class, Long.class, "use");
        Pencil pencil = new Pencil();
        boolean b = pencilIntegerAtomicReferenceFieldUpdater.compareAndSet(pencil, 0L, 1L);
        System.out.println(b);
    }

    private static class Pencil {
        volatile Integer use;
    }
}
