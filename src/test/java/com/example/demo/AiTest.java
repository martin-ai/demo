package com.example.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AiTest extends AiDemoApplicationTests {

    @Autowired
    private AiRemoteService aiRemoteService;

    @Test
    public void test() {
        aiRemoteService.getToken();
    }

}
