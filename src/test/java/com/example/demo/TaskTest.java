package com.example.demo;

import com.example.demo.thread.MultithreadingTaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskTest extends AiDemoApplicationTests {

    @Autowired
    private MultithreadingTaskService taskService;

    @Test
    public void test1() {
        taskService.testThread(IntStream.rangeClosed(0, 100).boxed().collect(Collectors.toList()));
    }

}
