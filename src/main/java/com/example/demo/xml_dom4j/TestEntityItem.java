package com.example.demo.xml_dom4j;

@XmlClass(value = "Item")
public class TestEntityItem {

    private String code;
    private String name;

    public TestEntityItem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
