package com.abao.java8.thread;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/11/22 13:31
 */
public class TimerTest {

    private Timer timer = new Timer();

    public void lanuchTimer() {
        timer.schedule(new TimerTask() {
            public void run() {
                throw new RuntimeException();
            }
        }, 1000 * 3, 500);
    }

    //向计时器添加一个任务
    public void addOneTask() {
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("hello world");
            }
        }, 1000 * 1, 1000 * 5);
    }

    public static void main(String[] args) throws InterruptedException {
        TimerTest test = new TimerTest();
        test.lanuchTimer();
        Thread.sleep(1000 * 5);//5秒钟之后添加一个新任务
        test.addOneTask();
    }
}
