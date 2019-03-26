package com.example.demo;

import com.example.demo.xml_jaxb.JaxbUtil;
import com.example.demo.xml_dom4j.*;
import com.example.demo.xml_jaxb.TestEntity2;
import com.example.demo.xml_jaxb.TestEntityItem2;
import com.google.common.collect.Lists;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.beans.IntrospectionException;

public class TestXml {

    @Test
    public void test() throws ReflectiveOperationException, IntrospectionException {
        TestEntity e = new TestEntity("xxx", Lists.newArrayList(new TestEntityItem("1231", "sahfka"), new TestEntityItem("2222", "asgasdga")));
        System.out.println(XmlUtils.generateXML(e, null));
    }

    @Test
    public void test2() throws JAXBException {
        TestEntity2 e = new TestEntity2("xxx", Lists.newArrayList(new TestEntityItem2("1231", "sahfka"), new TestEntityItem2("2222", "asgasdga")));
        System.out.println(JaxbUtil.beanToXml(e));
    }

}
