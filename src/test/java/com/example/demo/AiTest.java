package com.example.demo;

import com.example.demo.Ai.AiSimpleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AiTest extends AiDemoApplicationTests {

    @Autowired
    private AiSimpleService aiSimpleService;

    @Test
    public void test() {
        aiSimpleService.test();
    }

}
