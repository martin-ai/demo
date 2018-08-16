package com.example.demo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AsyncTask {

    public static Random random = new Random();

    public void d1() {
        try {
            this.doTaskOne();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void d2() {
        try {
            this.doTaskTwo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void d3() {
        try {
            this.doTaskThree();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void doTaskOne() throws InterruptedException {
        System.out.println("开始执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void doTaskTwo() throws InterruptedException {
        System.out.println("开始执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void doTaskThree() throws InterruptedException {
        System.out.println("开始执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
    }

}
