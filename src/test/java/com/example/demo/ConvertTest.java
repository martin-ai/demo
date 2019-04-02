package com.example.demo;

import com.example.demo.modelmapper.ConverterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConvertTest extends AiDemoApplicationTests {

    @Autowired
    private ConverterService converterService;

    @Test
    public void test1() {
        converterService.convert();
    }

}
