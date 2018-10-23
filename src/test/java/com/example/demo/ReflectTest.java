package com.example.demo;

import com.example.demo.reflect.ClassUtils;
import com.example.demo.reflect.ReflectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReflectTest extends AiDemoApplicationTests {
    @Autowired
    private ReflectService reflectService;

    //测试获取类对象
    @Test
    public void test1() throws ClassNotFoundException {
        reflectService.acquireClass();
    }

    //测试获取类实例
    //反射应用-工厂模式
    @Test
    public void test2() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        reflectService.acquireInstance("Man");
        reflectService.acquireInstance("Woman");
    }

    @Test
    public void test3() {
        String s = "hello";
        ClassUtils.getFieldMessage(s);
        ClassUtils.printConMessage(s);
        ClassUtils.getClassMethodMessage(s);
    }

}
