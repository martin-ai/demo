package com.example.demo.producer_consumer;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage<T> {

    /**
     * LinkedBlockingQueue是一个阻塞的线程安全的队列，底层采用链表实现
     * LinkedBlockingQueue构造的时候若没有指定大小capacity，则默认大小为Integer.MAX_VALUE
     * LinkedBlockingQueue不接受null
     */
    BlockingQueue<List<T>> queues = new LinkedBlockingQueue<>(10);

    /**
     * 添加元素的方法有三个：add,put,offer,且这三个元素都是向队列尾部添加元素的意思
     * add方法在添加元素的时候，若超出了度列的长度会直接抛出异常
     * put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素
     * offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false
     */
    public void push(List<T> p) throws InterruptedException {
        queues.put(p);
    }

    /**
     * 从队列中取出并移除头元素的方法有：poll，remove，take
     * poll: 若队列为空，返回null。
     * remove:若队列为空，抛出NoSuchElementException异常。
     * take:若队列为空，发生阻塞，等待有元素。
     */
    public List<T> pop() throws InterruptedException {
        return queues.take();
    }

}
