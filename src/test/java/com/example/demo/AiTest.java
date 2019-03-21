package com.example.demo;

import com.example.demo.nlp_baidu.AiSimpleService;
import com.example.demo.nlp_jieba.JiebaService;
import com.example.demo.nlp_maya.MyNLPSimpleService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AiTest extends AiDemoApplicationTests {

    @Autowired
    private AiSimpleService aiSimpleService;
    @Autowired
    private MyNLPSimpleService myNLPSimpleService;
    @Autowired
    private JiebaService jiebaService;

    final static List<String> words = Lists.newArrayList(
            "避免",
            "建义",
            "有亮",
            "功放",
            "防止",
            "延峰",
            "杨声器",
            "日强",
            "音箱",
            "远离"
    );

    final static List<String> contents = Lists.newArrayList(
            "五菱征程",
            "560 蓄电池异常亏电",
            "五菱荣光故障点亮加速没反应（偶发性）",
            "ABS灯，气囊灯，防侧滑灯有时会亮",
            "钥匙匹配",
            "转向无助力",
            "五菱荣光V遥控钥匙匹配",
            "客户反映该车下雨时右后门漏水。",
            "CN201空调控制模块屏幕显示异常",
            "CN112发动机抖动，故障码P0832"
    );

    @Test
    public void test() {
        for (String word : words) {
            aiSimpleService.wordEmbedding(word);
        }
    }

    @Test
    public void test2() {
        for (String word : words) {
            myNLPSimpleService.parse(word);
        }
    }

    @Test
    public void test3() {
        for (String content : contents) {
            Pair<List<String>, Integer> listIntegerPair = jiebaService.segment(content, false);
            System.out.println(listIntegerPair);
        }
    }

}
