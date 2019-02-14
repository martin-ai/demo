package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Forecast.DateQty;
import com.example.demo.Forecast.ForecastCommonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestForecast extends AiDemoApplicationTests {

    @Autowired
    private ForecastCommonService forecastCommonService;

    @Test
    public void test1() {
        String json = "[{\"qty\":12,\"date\":201607},\n" +
                "{\"qty\":0,\"date\":201608},\n" +
                "{\"qty\":0,\"date\":201609},\n" +
                "{\"qty\":0,\"date\":201610},\n" +
                "{\"qty\":0,\"date\":201611},\n" +
                "{\"qty\":0,\"date\":201612},\n" +
                "{\"qty\":0,\"date\":201701},\n" +
                "{\"qty\":0,\"date\":201702},\n" +
                "{\"qty\":25,\"date\":201703},\n" +
                "{\"qty\":26,\"date\":201704},\n" +
                "{\"qty\":32,\"date\":201705},\n" +
                "{\"qty\":21,\"date\":201706},\n" +
                "{\"qty\":0,\"date\":201707},\n" +
                "{\"qty\":23,\"date\":201708},\n" +
                "{\"qty\":27,\"date\":201709},\n" +
                "{\"qty\":36,\"date\":201710},\n" +
                "{\"qty\":35,\"date\":201711},\n" +
                "{\"qty\":28,\"date\":201712},\n" +
                "{\"qty\":45,\"date\":201801},\n" +
                "{\"qty\":31,\"date\":201802},\n" +
                "{\"qty\":35,\"date\":201803},\n" +
                "{\"qty\":21,\"date\":201804},\n" +
                "{\"qty\":8,\"date\":201805},\n" +
                "{\"qty\":12,\"date\":201806}]";
        List<DateQty> dateQties = JSONObject.parseArray(json, DateQty.class);   //走默认构造方法，并用setting方法加入值
//        System.out.println(JSONObject.toJSONString(dateQties)); //将有getting方法值转为json值
        forecastCommonService.filterInvalidData(forecastCommonService.filterInvalidData(dateQties), 1);
//        forecastCommonService.filterInvalidData(dateQties, 7);

    }

}
