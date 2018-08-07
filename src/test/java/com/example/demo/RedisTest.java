package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RedisTest extends AiDemoApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    public void testGet() {
        Map s = cacheService.getStr("s");
        System.out.println(JSONObject.toJSONString(s));
    }

    @Test
    public void testClean() {
        cacheService.clean();
    }

    @Test
    public void testUpdate() {
        Map s = cacheService.updateStr("s");
        System.out.println(JSONObject.toJSONString(s));
    }

}
