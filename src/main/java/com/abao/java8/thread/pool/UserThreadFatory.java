package com.abao.java8.thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/15 10:44
 */
public class UserThreadFatory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    public UserThreadFatory(String namePrefix) {
        this.namePrefix = "UserThreadFactory's" + namePrefix + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, r, name, 0);
        System.out.println(thread.getName());
        return thread;
    }
}
