package ch.chang.classLoader;

import sun.security.krb5.internal.PAData;

import java.util.Random;

public class LoadTime {

    public static void main(String[] args) {
        //parent 初始化
        //System.out.println(Parent.age);
        //System.out.println(Parent.random);
        //System.out.println(Child.age);


        //parent 不会被初始化
        //System.out.println(Parent.books);

        //子类不会被初始化
        //System.out.println(Child.age);
        //System.out.println(Child.iphone);

        //不会被初始化
        //System.out.println(Parent.books);
        //Parent [] parents = new Parent[5];
    }
}


class Parent {
    public static int age = 10;
    public static final int books = 100;
    public static final int random = new Random().nextInt();

    static {
        System.out.println("parent init");
    }
}

class Child extends Parent {
    public static int id = 10;
    public static final int iphone = 100;

    static {
        System.out.println("child init");
    }
}
