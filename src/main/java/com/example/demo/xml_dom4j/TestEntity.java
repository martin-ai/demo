package com.example.demo.xml_dom4j;

import java.util.Date;
import java.util.List;

@XmlClass(value = "Result")
public class TestEntity {

    private String test;
    private Date date = new Date();
    @XmlField(isClass = true)
    private List<TestEntityItem> items;

    public TestEntity(String test, List<TestEntityItem> items) {
        this.test = test;
        this.items = items;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<TestEntityItem> getItems() {
        return items;
    }

    public void setItems(List<TestEntityItem> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

