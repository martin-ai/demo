package com.example.demo;

import com.example.demo.event.PublishEventService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EventTest extends AiDemoApplicationTests {

    @Autowired
    private PublishEventService publishEventService;

    @Test
    public void test() {
        publishEventService.publish();
    }

}
