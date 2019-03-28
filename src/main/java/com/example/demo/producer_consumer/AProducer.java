package com.example.demo.producer_consumer;

import java.util.Collection;

public class AProducer extends BaseProducer<String, A> {

    public AProducer(String name, Storage<A> storage, Collection<String> requestList) {
        super(name, storage, requestList);
    }

    @Override
    public A to(String source) {
        return new A(source);
    }

}
