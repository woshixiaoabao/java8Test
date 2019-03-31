package com.abao.java8.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/3/26 15:01
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dosomething前");
        Object result= method.invoke(target, args);
        System.out.println("dosomething后");
        return result;
    }
}
