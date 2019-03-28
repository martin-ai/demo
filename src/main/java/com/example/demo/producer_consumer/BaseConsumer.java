package com.example.demo.producer_consumer;

import com.example.demo.BasicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class BaseConsumer<V, ID extends Serializable> implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String name;
    private Storage<V> storage;
    private BasicRepository<V, ID> basicRepository;

    public BaseConsumer(String name, Storage<V> storage, BasicRepository<V, ID> basicRepository) {
        this.name = name;
        this.storage = storage;
        this.basicRepository = basicRepository;
    }

    @Override
    public void run() {
        try {
            while (true) {
                basicRepository.saveAll(storage.pop());
                logger.info(String.format("%s consume products", name));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
