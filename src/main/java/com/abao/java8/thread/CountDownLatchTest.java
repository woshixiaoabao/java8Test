package com.abao.java8.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/11/19 10:02
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                cdl.countDown();
            });
        }
        try {
            System.out.println("等待3个子线程执行完毕...");
            cdl.await();
            System.out.println("3个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
