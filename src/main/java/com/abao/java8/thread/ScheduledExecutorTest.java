package com.abao.java8.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/3/16 9:25
 */
public class ScheduledExecutorTest {


    public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public void oldTask() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("运行时异常....");
                throw new RuntimeException();
            }
        }, 1000 * 3, 1000 * 10, TimeUnit.MILLISECONDS);
    }

    public void addOneTask() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("新任务.............");
            }
        }, 1000 * 1, 1000, TimeUnit.MILLISECONDS);
    }
    public static void main(String[] args) throws Exception {
        ScheduledExecutorTest test = new ScheduledExecutorTest();
        test.oldTask();
        Thread.sleep(8000);
        test.addOneTask();
    }
}
