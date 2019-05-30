package com.abao.java8.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/28 15:18
 */
public class CustomClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) throws IOException {
        // 从自定义路径中加载指定类

        File file = new File(name);
        final long length = file.length();
        byte[] raw = new byte[(int) length];

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            int r = fileInputStream.read(raw);
            if (r != length) {
                throw new IOException("无法读取全部文件：" + r + "!=" + length);
            }
            return raw;
        } finally {
            fileInputStream.close();
        }
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            final Class<?> clazz = Class.forName("com.abao.java8.classloader.CustomClassLoader", true, customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
