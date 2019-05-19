package ch.chang.classLoader;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    private static final String DEFAULT_DIR = "E:/lxc/item/idea/class-loader-test/";
    private String dir = DEFAULT_DIR;
    private String classLoaderName;

    public MyClassLoader() {
        super();
    }

    public MyClassLoader(String name) {
        super();
        this.classLoaderName = name;
    }

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(ClassLoader parent, String dir, String classLoaderName) {
        super(parent);
        this.dir = dir;
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String nowName = name.replace(".", "/");
        String fileName = nowName + ".class";
        File file = new File(this.dir, fileName);
        if (!file.exists()) {
            Class<?> supperClazz = getParent().loadClass(name);
            if (null == supperClazz) {
                throw new ClassNotFoundException("file not exist");
            }
            return supperClazz;

        }
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] cach = new byte[1024];
            int readSize;
            while ((readSize = fileInputStream.read(cach)) > 0) {
                byteArrayOutputStream.write(cach, 0, readSize);
            }
            byteArrayOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (IOException e) {
            throw new ClassNotFoundException();
        } finally {
            if (null != byteArrayOutputStream) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte[] targetByte = byteArrayOutputStream.toByteArray();
        if (null == targetByte || targetByte.length < 0) {
            throw new ClassNotFoundException();
        }
        return this.defineClass(name, targetByte, 0, targetByte.length);
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    public void setClassLoaderName(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }
}
