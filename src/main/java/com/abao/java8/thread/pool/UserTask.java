package com.abao.java8.thread.pool;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/15 11:14
 */
public class UserTask implements Runnable {

    private final AtomicLong count = new AtomicLong(0L);

    @Override
    public void run() {
        System.out.println("running_" + count.getAndIncrement());
    }
}
