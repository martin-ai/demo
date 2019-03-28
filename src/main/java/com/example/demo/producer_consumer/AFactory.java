package com.example.demo.producer_consumer;

import com.example.demo.SpringApplicationContext;

import java.util.Collection;

public class AFactory implements IFactory<String> {

    private Storage<A> storage = new Storage<>();

    @Override
    public BaseProducer createProducer(String name, Collection<String> requestList) {
        return new AProducer(name, storage, requestList);
    }

    @Override
    public BaseConsumer createConsumer(String name) {
        return new AConsumer(name, storage, SpringApplicationContext.getBean(ARepository.class));
    }

}
