package com.example.demo.reflect;

import org.springframework.stereotype.Service;

/*
 *
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意方法和属性；
 * 这种动态获取信息以及动态调用对象方法的功能称为JAVA语言的反射机制
 *
 * */
@Service
public class ReflectService {

    public final String CLASS_PREFIX = "com.example.demo.reflect.";

    public void acquireClass() throws ClassNotFoundException {
        //第一种，任何一个类都有一个隐含的静态成员变量class
        Class c1 = Man.class;
        //第二种，已经知道该类的对象，通过getClass()获得
        Man man = new Man();
        Class c2 = man.getClass();
        //第三种，Class类的forName()方法
        Class c3 = Class.forName("com.example.demo.reflect.Man");
        //这里，c1,c2,c3都是Class类的实例，我们称c1, c2 ,c3为Person类的类类型
        //不难看出，c1 == c2结果是true, c2 == c3结果也是true
        System.out.println(c1 == c2);
        System.out.println(c3 == c2);
    }

    public void acquireInstance(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(CLASS_PREFIX + name);
        Person person = (Person) c.newInstance();
        person.say();
    }

}
