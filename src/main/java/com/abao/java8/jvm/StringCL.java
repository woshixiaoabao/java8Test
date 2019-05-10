package com.abao.java8.jvm;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author anpei
 * @version 1.0
 * @Description: 显示类加载，获取方法签名
 * @date 2019/4/15 20:00
 */
public class StringCL {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clzStr = Class.forName("java.lang.String");

        final Method[] methods = clzStr.getDeclaredMethods();
        for (Method m : methods) {
            // 获取修饰符标志的字符串
            String mod = Modifier.toString(m.getModifiers());
            System.out.print(mod + " " + m.getName() + "(");
            Class<?>[] ps = m.getParameterTypes();
            if (ps.length == 0)
                System.out.print(')');
            for (int i = 0; i < ps.length; i++) {
                char end = i == ps.length - 1 ? ')' : ',';
                System.out.print(ps[i].getSimpleName() + end);
            }
            System.out.println();
        }
    }
}
