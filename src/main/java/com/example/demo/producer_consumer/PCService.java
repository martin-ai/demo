package com.example.demo.producer_consumer;

import com.example.demo.nlp_jieba.StopWordDictionary;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.google.common.collect.Iterators.partition;

@Service
public class PCService {

    private static final int TASK_THREAD_SIZE = 10;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() throws InterruptedException {
        Set<String> requestList = StopWordDictionary.getInstance().getStopWords();
        logger.info(String.format("need process word size : %d", requestList.size()));

        IFactory<String> factory = new AFactory();

        int num = TASK_THREAD_SIZE;
        List<Future<Boolean>> futureP = Lists.newArrayList();
        ExecutorService serviceP = Executors.newCachedThreadPool();
        if (requestList.size() / TASK_THREAD_SIZE <= 1) {
            futureP.add(serviceP.submit(factory.createProducer("P", requestList)));
        } else {
            UnmodifiableIterator<List<String>> iterator = partition(requestList.iterator(), requestList.size() / TASK_THREAD_SIZE);
            while (iterator.hasNext()) {
                futureP.add(serviceP.submit(factory.createProducer(String.format("P%d", TASK_THREAD_SIZE - (num--) + 1), iterator.next())));
            }
        }
        logger.info("Producer stand by");

        ExecutorService serviceC = Executors.newCachedThreadPool();
        serviceC.submit(factory.createConsumer("C1"));
        serviceC.submit(factory.createConsumer("C2"));
        logger.info("Consumer stand by");

        boolean dog = futureP.stream().map(x -> {
            try {
                return x.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return false;
        }).reduce(Boolean::logicalAnd).orElse(false);

        logger.info("dog :" + dog);
        if (dog) {
            serviceP.shutdownNow();
            Thread.sleep(10000);
            serviceC.shutdownNow();
            logger.info("Producer Consumer finished");
        }
    }
}
