package com.example.demo;

import com.example.demo.nlp_baidu.AiSimpleService;
import com.example.demo.nlp_maya.MyNLPSimpleService;
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
//            "避免",
//            "建义",
//            "有亮",
//            "功放",
//            "防止",
//            "延峰",
//            "杨声器",
//            "日强",
//            "音箱",
            "远离"
    );

    @Test
    public void test() {
        for (String content : contents) {
            aiSimpleService.wordEmbedding(content);
        }
    }

    @Test
    public void test2() {
        for (String content : contents) {
            myNLPSimpleService.parse(content);
        }
    }

}
