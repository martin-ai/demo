package com.example.demo.reflect;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ClassUtils {

    /**
     * 获取成员函数的信息
     */
    public static void getClassMethodMessage(Object obj, ReturnTypeEnum returnTypeEnum) {
        Class c = obj.getClass();
        //获取类的名称
        System.out.println(String.format("当前类:%s", c.getName()));

        /**
         *
         * Method类，方法对象
         *getMethods方法获得所有public的方法，包括继承而来
         *getDeclaredMethods是获取该类自己的声明的方法
         */
        Method[] ms = c.getMethods();
        List<Method> methodList = Lists.newArrayList(ms);
        if (returnTypeEnum != null) {
            methodList = methodList.stream().filter(x -> StringUtils.upperCase(x.getReturnType().getSimpleName()).startsWith(returnTypeEnum.name())).collect(Collectors.toList());
        }

        for (Method m : methodList) {
            //得到方法的返回值类型的类类型
            Class returnType = m.getReturnType();
            System.out.print(String.format("返回类型：%s ", returnType.getSimpleName()));
            //得到方法的名称
            System.out.print(String.format("方法：%s(", m.getName()));
            //获取参数类型
            Class[] paramTypes = m.getParameterTypes();
            Iterator<Class> classIterable = Lists.newArrayList(paramTypes).iterator();
            while (classIterable.hasNext()) {
                Class cc = classIterable.next();
                System.out.print(cc.getSimpleName());
                if (classIterable.hasNext()) {
                    System.out.print(",");
                }
            }
            System.out.println(")");
        }
    }

    public static void getFieldMessage(Object obj) {
        Class c = obj.getClass();

        /**
         * 成员变量也是对象
         * java.lang.reflect.Field
         *
         */
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            //得到成员变量类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //得到成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    public static void printConMessage(Object obj) {
        Class c = obj.getClass();
        /**
         * 构造函数也是对象
         *
         */
        Constructor[] cs = c.getConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "(");
            //获取构造函数参数列表------>参数列表的参数类型
            Class[] paramType = constructor.getParameterTypes();
            for (Class class1 : paramType) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")");
        }

    }

}
