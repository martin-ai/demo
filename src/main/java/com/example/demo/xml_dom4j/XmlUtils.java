package com.example.demo.xml_dom4j;

import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class XmlUtils {

    public static String generateXML(Object o, Document document) throws ReflectiveOperationException, IntrospectionException {
        //获取对象o的类信息
        Class c = o.getClass();
        //判断是否有XmlClass注解
        if (c.isAnnotationPresent(XmlClass.class)) {
            //获取XmlClass注解
            XmlClass xmlClass = (XmlClass) c.getAnnotation(XmlClass.class);
            Element root;
            if (document == null) {
                //如果xml文档为空，则创建一个document，并为其创建根节点，并在根节点上创建当前节点
                document = DocumentHelper.createDocument();
                root = document.addElement(xmlClass.value());
            } else {
                //如果xml文档不为空，则获取其根节点，并在根节点上创建当前节点
                root = document.getRootElement().addElement(xmlClass.value());
            }
            //获得所有声明的字段
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                //判断是否有XmlField注解
                if (f.isAnnotationPresent(XmlField.class)) {
                    dealWithAnnotation(o, f, document, root);
                } else {
                    dealWithNoAnnotation(o, f, root);
                }
            }
        }
        return document == null ? null : document.asXML();
    }

    private static void dealWithAnnotation(Object o, Field f, Document document, Element root) throws ReflectiveOperationException, IntrospectionException {
        //获取XmlField注解
        XmlField xmlField = f.getAnnotation(XmlField.class);
        if (!xmlField.ignore()) {
            //判断是否是list类型
            if (List.class.isAssignableFrom(f.getType())) {
                //判断是否是自定义类
                if (xmlField.isClass()) {
                    List<Object> objectList = getFieldDataList(getFieldData(o, f));
                    for (Object object : objectList) {
                        generateXML(object, document);
                    }
                } else {
                    root.addElement(f.getName()).addText(String.valueOf(getFieldData(o, f)));
                }
            } else {
                //判断是否是自定义类
                if (xmlField.isClass()) {
                    generateXML(getFieldData(o, f), document);
                } else {
                    root.addElement(f.getName()).addText(String.valueOf(getFieldData(o, f)));
                }
            }
        }
    }

    private static void dealWithNoAnnotation(Object o, Field f, Element root) throws ReflectiveOperationException, IntrospectionException {
        //判断是否是list类型 没有注解一定不是自定义类 否则解析会有问题
        if (List.class.isAssignableFrom(f.getType())) {
            List<Object> fieldDataList = getFieldDataList(getFieldData(o, f));
            for (Object fieldData : fieldDataList) {
                root.addElement(f.getName()).addText(String.valueOf(fieldData));
            }
        } else {
            root.addElement(f.getName()).addText(String.valueOf(getFieldData(o, f)));
        }
    }

    //通过反射机制获取当前Object中的指定field数据
    private static Object getFieldData(Object o, Field f) throws ReflectiveOperationException, IntrospectionException {
        PropertyDescriptor pd = new PropertyDescriptor(f.getName(), o.getClass());
        Method rM = pd.getReadMethod();
        return rM.invoke(o);
    }

    //通过反射机制获取list中的数据
    private static List<Object> getFieldDataList(Object o) throws ReflectiveOperationException {
        List<Object> objectList = Lists.newArrayList();
        //拿到list类中的size方法
        Method mSize = List.class.getDeclaredMethod("size");
        //拿到list类中的get方法
        Method mGet = List.class.getDeclaredMethod("get", int.class);
        for (int i = 0; i < (int) mSize.invoke(o); i++) {
            objectList.add(mGet.invoke(o, i));
        }
        return objectList;
    }

}
