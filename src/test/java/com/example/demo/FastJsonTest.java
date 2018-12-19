package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.fastjson.TestBean1;
import com.example.demo.fastjson.TestBean2;
import org.junit.Test;

public class FastJsonTest extends AiDemoApplicationTests {

    @Test
    public void t1() {
        //fastjson 1.2.38兼容这种模式
        String s = "{'part_code':'111','partName':'xxx'}";
        TestBean1 testBean1 = JSON.parseObject(s, TestBean1.class);
        TestBean2 testBean2 = JSON.parseObject(s, TestBean2.class);
        System.out.println(JSON.toJSONString(testBean1));
        System.out.println(JSON.toJSONString(testBean2));

    }

}
