package ch.chang.concurrent.unsafe;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {


    /**
     * 并没有执行constructor 去创建对象
     * 但是执行了static 代码块
     *
     * @throws InstantiationException
     */
    @Test
    public void createObject() throws InstantiationException {
        Unsafe unSafe = getUnSafe();
        Rule rule = (Rule) unSafe.allocateInstance(Rule.class);
    }

    /**
     * @throws InstantiationException
     * @throws NoSuchFieldException   设置一个新值
     */
    @Test
    public void updateFieldValue() throws InstantiationException, NoSuchFieldException {
        Unsafe unsafe = getUnSafe();
        Rule rule = (Rule) unsafe.allocateInstance(Rule.class);
        //偏移量
        long offset = unsafe.objectFieldOffset(Rule.class.getDeclaredField("temp"));
        unsafe.putInt(rule, offset, 2);
        System.out.println(rule.temp);
    }

    private static Unsafe getUnSafe() {
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            Unsafe unsafe = (Unsafe) declaredField.get(null);
            return unsafe;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Rule {
        private int temp = 0;

        static {
            System.out.println("static block");
        }

        public Rule() {
            temp = 1;
            System.out.println("constructor");
        }
    }
}
