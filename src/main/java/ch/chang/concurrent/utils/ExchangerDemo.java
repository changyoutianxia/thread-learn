package ch.chang.concurrent.utils;

import com.mysql.cj.util.TimeUtil;
import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 用于两个线程交换数据
 * 1、如果一个线程挂掉，则另外一个线程陷入一直等待中
 * 2、交换的数据是地址，不是拷贝
 * 3、可以一直交换数据
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Student> studentExchanger = new Exchanger<>();
        new Thread(() -> {
            Student zhangsan = new Student("zhangsan");
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " exchange student zhangsan  start " + zhangsan);
                Student exchange = studentExchanger.exchange(zhangsan);
                System.out.println(Thread.currentThread().getName() + " exchange student end,get value:" + exchange.getName() + " : " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            Student lisi = new Student("lisi");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " exchange student lisi  start " + lisi);
                Student exchange = studentExchanger.exchange(lisi);
                System.out.println(Thread.currentThread().getName() + " exchange student end,get value:" + exchange.getName() + " : " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

    }

    @Test
    public void testOneThreadOver() throws InterruptedException {
        Exchanger<Student> studentExchanger = new Exchanger<>();
        Thread t1 = new Thread(() -> {
            Student zhangsan = new Student("zhangsan");
            try {
                System.out.println(Thread.currentThread().getName() + " exchange student zhangsan  start " + zhangsan);
                Thread.sleep(10000);
                Student exchange = studentExchanger.exchange(zhangsan);
                System.out.println(Thread.currentThread().getName() + " exchange student end,get value:" + exchange.getName() + " : " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "A");
        Thread t2 = new Thread(() -> {
            Student lisi = new Student("lisi");
            try {
                System.out.println(Thread.currentThread().getName() + " exchange student lisi  start " + lisi);
                Student exchange = studentExchanger.exchange(lisi,2, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " exchange student end,get value:" + exchange.getName() + " : " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }, "B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static class Student {
        private String name;

        public Student() {
        }

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
