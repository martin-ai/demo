package com.example.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * new Thread的弊端
 * a. 每次new Thread新建对象性能差。
 * b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
 * c. 缺乏更多功能，如定时执行、定期执行、线程中断。
 * <p>
 * 相比new Thread，Java提供的四种线程池的好处在于：
 * a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
 * b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
 * c. 提供定时执行、定期执行、单线程、并发数控制等功能。
 * <p>
 * ThreadPoolExecutor 构造方法
 * public ThreadPoolExecutor(
 * int corePoolSize,                      //线程池核心线程数量
 * int maximumPoolSize,                   //线程池最大线程数量
 * long keepAliveTime,                    //线程存活时间
 * TimeUnit unit,                         //
 * BlockingQueue<Runnable> workQueue,     //线程使用阻塞队列
 * ThreadFactory threadFactory,           //创建线程池工厂
 * RejectedExecutionHandler handler       //线程池拒绝策略
 * )
 */
public class ThreadPoolStudy {

    public static void main(String[] args) throws InterruptedException {
        studyScheduledThreadPool();
    }

    // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
    // 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
    // 注：用于执行一些生存期很短的异步型任务。不适用于IO等长延时操作，因为这可能会创建大量线程，导致系统崩溃。
    public static void studyCachedThreadPool() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            Thread.sleep(index * 10);
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(index * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + index);
                }
            });
        }
    }

    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    //因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
    //注：1.定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
    //2.适用一些很稳定很固定的正规并发线程，多用于服务器
    public static void studyFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //创建一个定长线程池，支持定时及周期性任务执行
    //schedule 表示延迟3秒执行
    //scheduleAtFixedRate 表示延迟1秒后每3秒执行一次
    public static void studyScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":delay 1 seconds, and execute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
    // 适用于有明确执行顺序但是不影响主线程的任务，压入池中的任务会按照队列顺序执行。
    public static void studySingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
