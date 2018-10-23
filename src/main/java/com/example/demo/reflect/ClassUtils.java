package com.example.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtils {

    /**
     * 获取成员函数的信息
     */
    public static void getClassMethodMessage(Object obj) {
        Class c = obj.getClass();
        //获取类的名称
        //System.out.println(c.getName());

        /**
         *
         * Method类，方法对象
         *getMethods方法获得所有public的方法，包括继承而来
         *getDeclaredMethods是获取该类自己的声明的方法
         */
        Method[] ms = c.getMethods();
        for (int i = 0; i < ms.length; i++) {
            //得到方法的返回值类型的类类型
            Class returnType = ms[i].getReturnType();
            System.out.print(returnType.getName() + " ");
            //得到方法的名称
            System.out.print(ms[i].getName() + "(");
            //获取参数类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName() + ",");
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
