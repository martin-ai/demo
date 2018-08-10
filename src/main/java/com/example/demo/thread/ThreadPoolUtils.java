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

}
