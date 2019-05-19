package ch.chang.classLoader;

public class MyClassLoaderMain {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class<?> clazz = myClassLoader.findClass("ch.chang.classLoader.ClassLoadTarget");
        //ch.chang.classLoader.MyClassLoader@4554617c
        System.out.println(((MyClassLoader) clazz.getClassLoader()).getClassLoaderName());


        MyClassLoader myClassLoader2 = new MyClassLoader(myClassLoader, "myClassLoader2");
        myClassLoader2.setDir("d:/");
        Class<?> parantClazz = myClassLoader2.findClass("ch.chang.classLoader.ClassLoadTarget");
        //ch.chang.classLoader.MyClassLoader@4554617c
        System.out.println(((MyClassLoader) parantClazz.getClassLoader()).getClassLoaderName());
    }
}
