package com.example.demo.producer_consumer;

import com.example.demo.BasicRepository;

public class AConsumer extends BaseConsumer<A, String> {

    public AConsumer(String name, Storage storage, BasicRepository basicRepository) {
        super(name, storage, basicRepository);
    }

}
