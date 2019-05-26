package ch.chang.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class AtomicReferenceDemo {
    @Test
    public void test() {
        Person zs = new Person("zs", "01");
        AtomicReference<Object> objectAtomicReference = new AtomicReference<>();
        objectAtomicReference.set(zs);
        Person old = new Person("zs", "01");

        zs.setName("wang");
        Person ls = new Person("ls", "02");
        //false
        System.out.println(objectAtomicReference.compareAndSet(old, ls));
        //true ,可见比较的是引用的地址
        System.out.println(objectAtomicReference.compareAndSet(zs, ls));
    }
}

class Person {
    private String name;
    private String id;

    public Person() {
    }

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
