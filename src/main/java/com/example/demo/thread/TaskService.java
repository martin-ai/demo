package com.example.demo.thread;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Service
public class TaskService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public void testThread(List<Integer> params) {
        List<Callable<Integer>> tasks = Lists.newArrayList();
        params.forEach(num -> {
            tasks.add(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(String.format("%s - %d", threadName, num));
                return 0;
            });
        });
        try {
            ThreadPoolUtils.doExecute(tasks);
        } catch (Exception e) {
            logger.error("failed", e);
        }
    }

}
