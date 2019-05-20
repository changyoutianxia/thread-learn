package ch.chang.classLoader;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcClassLOader {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /**
         *     public static Class<?> forName(String className)
         *                 throws ClassNotFoundException {
         *         Class<?> caller = Reflection.getCallerClass();
         *         return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
         *     }
         *
         *     static {
         *         try {
         *             java.sql.DriverManager.registerDriver(new Driver());
         *         } catch (SQLException E) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         *
         *
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(Class.class.getClassLoader());
        System.out.println(JdbcClassLOader.class.getClassLoader());
        /**
         *
         *   public static Connection getConnection(String url)
         *         throws SQLException {
         *
         *         java.util.Properties info = new java.util.Properties();
         *         return (getConnection(url, info, Reflection.getCallerClass()));
         *     }
         *
         *     ClassLoader callerCL = caller != null ? caller.getClassLoader() : null;
         *         synchronized(DriverManager.class) {
         *             // synchronize loading of the correct classloader.
         *             if (callerCL == null) {
         *                 callerCL = Thread.currentThread().getContextClassLoader();
         *             }
         *         }
         *
         *      使用当前线程的加载器
         *      private static boolean isDriverAllowed(Driver driver, ClassLoader classLoader) {
         *         boolean result = false;
         *         if(driver != null) {
         *             Class<?> aClass = null;
         *             try {
         *                 aClass =  Class.forName(driver.getClass().getName(), true, classLoader);
         *             } catch (Exception ex) {
         *                 result = false;
         *             }
         *
         *              result = ( aClass == driver.getClass() ) ? true : false;
         *         }
         *
         *         return result;
         *     }
         *  在jdbc4 以后可以使用在/META-INF/services/java.sql.Driver
         *  表明使用驱动
         *  DriverManager 在注册的时候会去调用,获取线程的上下文加载器，然后使用线程的上下文加载器来加载这个类
         *
         */
        DriverManager.getConnection("");
    }
}
