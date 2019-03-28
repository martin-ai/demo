package com.example.demo.producer_consumer;

import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import static com.google.common.collect.Iterators.partition;


public abstract class BaseProducer<S, D> implements Callable<Boolean> {

    private static final int PRODUCER_HANDLER_SIZE = 2;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseProducer.class);

    private String name;
    private Collection<S> requestList;
    private Storage<D> storage;

    public BaseProducer(String name, Storage<D> storage, Collection<S> requestList) {
        this.name = name;
        this.storage = storage;
        this.requestList = requestList;
    }

    @Override
    public Boolean call() {
        int counter = 1;
        try {
            UnmodifiableIterator<List<S>> iterator = partition(requestList.iterator(), requestList.size() / PRODUCER_HANDLER_SIZE);
            while (iterator.hasNext()) {
                List<D> products = Lists.newArrayList();
                Collection<S> sourceList = iterator.next();
                for (S source : sourceList) {
                    products.add(to(source));
                }
                storage.push(products);
                LOGGER.info(String.format("%s produce %d/%d products", name, counter++, PRODUCER_HANDLER_SIZE));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public abstract D to(S source);

}
