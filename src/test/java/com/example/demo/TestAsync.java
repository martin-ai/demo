package com.example.demo;

import com.example.demo.async.AsyncTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAsync extends AiDemoApplicationTests {

    @Autowired
    private AsyncTask task;

    @Test
    public void test1() throws InterruptedException {
        System.out.println("开始执行Controller任务");
        long start = System.currentTimeMillis();
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
        long end = System.currentTimeMillis();
        System.out.println("完成Controller任务，耗时：" + (end - start) + "毫秒");
        Thread.sleep(10 * 1000);
    }

}
