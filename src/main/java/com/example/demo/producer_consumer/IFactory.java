package com.example.demo.producer_consumer;

import java.util.Collection;

public interface IFactory<V> {

    BaseProducer createProducer(String name, Collection<V> requestList);

    BaseConsumer createConsumer(String name);

}
