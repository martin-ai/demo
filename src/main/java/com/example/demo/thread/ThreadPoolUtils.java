package com.example.demo.thread;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolUtils {

    public static void doExecute(List<Callable<Integer>> tasks) throws Exception {
        if (CollectionUtils.isEmpty(tasks)) {
            return;
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(tasks.size());
        List<Future<Integer>> futures = threadPool.invokeAll(tasks); // 阻塞，直到所有任务都运行完毕
        futures.forEach(r -> {
            try {
                r.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * API:
     * shutdown:启动一个关闭命令，不再接受新任务，当所有已提交任务执行完后，就关闭。
     * isShutdown:如果此执行程序已关闭，则返回 true。
     * isTerminated:如果关闭后所有任务都已完成，则返回 true。除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。
     * awaitTermination:等待（阻塞）直到关闭或最长等待时间或发生中断
     * 注意:如果此执行程序终止（关闭），则返回 true；如果终止前超时期满，则返回 false
     */
    static void shutdownAndAwaitTermination(ExecutorService pool) {
        //第一阶段调用 shutdown 拒绝传入任务
        pool.shutdown();
        //第二阶段等5秒后，任务还没执行完成，就调用 shutdownNow（如有必要）取消所有遗留的任务
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                //第一次等待60秒后 任务未完成 尝试shutdownNom
                System.out.println("第一次等待5秒后 任务未完成 尝试shutdownNom");
                pool.shutdownNow();
                if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("第二次等待5秒后任务未完成 报错");
                    //第二次等待60秒后任务未完成 报错
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
