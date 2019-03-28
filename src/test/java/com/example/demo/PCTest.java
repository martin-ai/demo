package com.example.demo;

import com.example.demo.producer_consumer.PCService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PCTest extends AiDemoApplicationTests {

    @Autowired
    private PCService pcService;

    @Test
    public void test() throws InterruptedException {
        pcService.run();
    }

}
