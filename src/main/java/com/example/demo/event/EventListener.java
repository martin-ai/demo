package com.example.demo.event;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by yixiang.guo on 2018/8/23.
 */
@Component
public class EventListener implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        System.out.println(JSONObject.toJSONString(testEvent));
    }

}
