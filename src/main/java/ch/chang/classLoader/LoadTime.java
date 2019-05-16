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

        /*x=0 y=1
         *  x=0
         *  y=0
         *  x=x+1;
         *  y=y+1;
         *  x=0;
         */

        //System.out.println(Book.x);
        //System.out.println(Book.y);

        /*x=0 y=1
         *  x=0
         *  y=0
         *
         *  x=0
         *  x=x+1;
         *  y=y+1;
         *
         */
        //System.out.println(Toy.x);
        //System.out.println(Toy.y);
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

class Book {
    public static Book book = new Book();
    public static int x = 0;
    public static int y;

    public Book() {
        x++;
        y++;
    }
}

class Toy {

    public static int x = 0;
    public static int y;
    public static Toy toy = new Toy();

    public Toy() {
        x++;
        y++;
    }

}
