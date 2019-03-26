package com.example.demo.xml_jaxb;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "RESULT")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TestEntity2 {

    private String test;
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    private Date date = new Date();
    @XmlElement(name = "ITEM", nillable = true)
    @XmlElementWrapper(name = "ITEMS")
    private List<TestEntityItem2> items;

    public TestEntity2() {
    }

    public TestEntity2(String test, List<TestEntityItem2> items) {
        this.test = test;
        this.items = items;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<TestEntityItem2> getItems() {
        return items;
    }

    public void setItems(List<TestEntityItem2> items) {
        this.items = items;
    }

}
