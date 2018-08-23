package com.example.demo.event;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishEventService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publish() {
        List<String> testEventBeans = Lists.newArrayList("1", "2");
        eventPublisher.publishEvent(new TestEvent(this, testEventBeans));
    }

}
