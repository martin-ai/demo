package com.example.demo.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java多线程-并发协作(生产者消费者模型)
 * 准确说应该是“生产者-消费者-仓储”模型，离开了仓储，生产者消费者模型就显得没有说服力了。
 * 对于此模型，应该明确一下几点：
 * 1、生产者仅仅在仓储未满时候生产，仓满则停止生产。
 * 2、消费者仅仅在仓储有产品时候才能消费，仓空则等待。
 * 3、当消费者发现仓储没产品可消费时候会通知生产者生产。
 * 4、生产者在生产出可消费产品时候，应该通知等待的消费者去消费
 * <p>
 * ProducerConsumer是主类，Producer生产者，Consumer消费者，Product产品，Storage仓库
 */

public class ProducerConsumer {

    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Storage s = pc.new Storage();
        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = pc.new Producer("张三", s);
        Producer p2 = pc.new Producer("李四", s);
        Consumer c = pc.new Consumer("王五", s);
        Consumer c2 = pc.new Consumer("老刘", s);
        Consumer c3 = pc.new Consumer("老林", s);
        service.submit(p);
        service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);
        ThreadPoolUtils.shutdownAndAwaitTermination(service);
    }

    //生产者
    class Producer implements Runnable {
        private String name;
        private Storage storage;

        public Producer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                while (num.intValue() < 10) {
                    Product product = new Product((int) (Math.random() * 10000));
                    System.out.println(name + "准备生产(" + product.toString() + ")");
                    storage.push(product);
                    System.out.println(name + "已生产(" + product.toString() + ")");
                    System.out.println("===============");
                    num.addAndGet(1);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费者
    class Consumer implements Runnable {
        private String name;
        private Storage storage;

        public Consumer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(name + "准备消费产品");
                    Product product = storage.pop();
                    System.out.println(name + "已消费（" + product.toString() + ")");
                    System.out.println("======================");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //仓库
    class Storage {
        BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10);

        public void push(Product p) throws InterruptedException {
            queues.put(p);
        }

        public Product pop() throws InterruptedException {
            return queues.take();
        }
    }

    //产品
    class Product {
        private int id;

        public Product(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "产品：" + this.id;
        }
    }

}


