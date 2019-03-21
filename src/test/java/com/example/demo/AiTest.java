package com.example.demo;

import com.example.demo.baidunlp.AiSimpleService;
import com.example.demo.mynlp.MyNLPSimpleService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AiTest extends AiDemoApplicationTests {

    @Autowired
    private AiSimpleService aiSimpleService;
    @Autowired
    private MyNLPSimpleService myNLPSimpleService;

    final static List<String> contents = Lists.newArrayList(
            "不是发这里，请发TAC@sgmw.com.cn邮箱，附上录音"
    );

    @Test
    public void test() {
        for (String content : contents) {
            aiSimpleService.lexer(content);
        }
    }

    @Test
    public void test2() {
        for (String content : contents) {
            myNLPSimpleService.parse(content);
        }
    }

}
