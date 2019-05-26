package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 同AtomicReference 相同多了一个版本号，防止ABA问题
 */
public class AtomicStampedReferenceDemo {
    @Test
    public void test() {
        Person zs = new Person("zs", "01");
        AtomicStampedReference<Person> objectAtomicReference = new AtomicStampedReference<Person>(zs, 1);
        boolean b = objectAtomicReference.compareAndSet(zs, zs, 1, 2);
        System.out.println(b);
    }
}
