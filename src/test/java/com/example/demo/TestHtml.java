package com.example.demo;

import com.example.demo.html.HtmlConvertService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class TestHtml extends AiDemoApplicationTests {

    @Autowired
    private HtmlConvertService htmlConvertService;

    @Test
    public void test1() {
        Map<String, String> result = Maps.newHashMap();
        htmlConvertService.resolveTOC("1.htm", result);
        System.out.println(result);
    }

}
