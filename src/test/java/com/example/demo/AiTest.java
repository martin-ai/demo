package com.example.demo;

import com.example.demo.Ai.AiSimpleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AiTest extends AiDemoApplicationTests {

    @Autowired
    private AiSimpleService aiSimpleService;

    @Test
    public void test() {
//        aiSimpleService.dnnlmCn("北京");
//        aiSimpleService.commentTag("蔚来汽车电池不给力");
        String title = "iphone手机出现“白苹果”原因及解决办法，用苹果手机的可以看下";
        String content = "如果下面的方法还是没有解决你的问题建议来我们门店看下成都市锦江区红星路三段99号银石广场24层01室。";
        aiSimpleService.wordSimEmbedding(title, content);
    }

}
