package com.example.demo.event;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Created by yixiang.guo on 2018/8/23.
 */
public class TestEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4400730833637546422L;
    private List<String> params = Lists.newArrayList();

    public TestEvent(Object source, List<String> params) {
        super(source);
        this.params = params;
    }

    public List<String> getParams() {
        return params;
    }

}
