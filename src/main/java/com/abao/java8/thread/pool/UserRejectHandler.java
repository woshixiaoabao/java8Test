package com.abao.java8.thread.pool;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/15 11:29
 */
public class UserRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejected." + executor.toString());
    }
}
