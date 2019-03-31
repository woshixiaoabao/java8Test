package com.abao.java8.agent;

import java.lang.reflect.Proxy;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/3/26 14:55
 */
public class MyDynamicTest {
    public static void main(String[] args) {
        DoSometing ds = new DoSomethingImpl();
        MyInvocationHandler handler = new MyInvocationHandler(ds);
        DoSometing doSometing = (DoSometing) Proxy.newProxyInstance(DoSomethingImpl.class.getClassLoader(),
                DoSomethingImpl.class.getInterfaces(), handler);
        doSometing.sayHello();
    }
}
