package com.abao.java8.thread.pool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/15 11:44
 */
public class UserThreadPool {
    public static void main(String[] args) {
        BlockingDeque queue = new LinkedBlockingDeque(2);

        UserThreadFatory f1 = new UserThreadFatory("第一机房");
        UserThreadFatory f2 = new UserThreadFatory("第二机房");

        UserRejectHandler handler = new UserRejectHandler();

        ThreadPoolExecutor threadPoolFirst = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, queue, f1, handler);
        ThreadPoolExecutor threadPoolSecond = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, queue, f2, handler);

        Runnable task = new UserTask();
        for (int i = 0; i < 200; i++) {
            threadPoolFirst.execute(task);
            threadPoolSecond.execute(task);
        }
    }
}
