package com.example.demo.stream;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

@Service
public class StreamService {

    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    //lambda 处理大数据能力分析
    public void handleWithoutLambda(List<Integer> testDataList) {
        long ts = System.currentTimeMillis();
        List<Point> pointList = Lists.newArrayList();
        for (Integer i : testDataList) {
            pointList.add(new Point(i % 3, i));
        }
        double max = Double.MIN_VALUE;
        for (Point p : pointList) {
            max = Math.max(p.distance(0, 0), max);
        }
        System.out.println(max);
        ts = System.currentTimeMillis() - ts;
        System.out.println("handle without lambda cost time ：" + ts);
    }


    public void handleWithinLambda(List<Integer> testDataList) {
        long ts = System.currentTimeMillis();
        OptionalDouble max = testDataList.stream().map(i -> new Point(i % 3, i)).mapToDouble(p -> p.distance(0, 0)).max();
        System.out.println(max.getAsDouble());
        ts = System.currentTimeMillis() - ts;
        System.out.println("handle within lambda cost time ：" + ts);
    }

    public void handleWithinParallelLambda(List<Integer> testDataList) {
        long ts = System.currentTimeMillis();
        OptionalDouble max = testDataList.parallelStream().map(i -> new Point(i % 3, i)).mapToDouble(p -> p.distance(0, 0)).max();
        System.out.println(max.getAsDouble());
        ts = System.currentTimeMillis() - ts;
        System.out.println("handle within lambda cost time ：" + ts);
    }

    //lambda parallelStream 线程安全分析
    public void test() {
        IntStream.range(0, 10000).forEach(list1::add);
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });
        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());
    }

    private List<Integer> nums = new ArrayList<>();
    private Set<Double> result = new HashSet<>();

    //lambda parallelStream 自定义现实Collector接口
    public void run() {
        // 填充原始数据，nums中填充0-9 10个数
        IntStream.range(0, 10).forEach(nums::add);
        //实现Collector接口
        result = nums.stream().parallel().collect(new Collector<Integer, Container, Set<Double>>() {
            @Override
            public Supplier<Container> supplier() {
                return Container::new;
            }

            @Override
            public BiConsumer<Container, Integer> accumulator() {
                return Container::accumulate;
            }

            @Override
            public BinaryOperator<Container> combiner() {
                return Container::combine;
            }

            @Override
            public Function<Container, Set<Double>> finisher() {
                return Container::getResult;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        });

        System.out.println("原始数据：");
        this.nums.forEach(i -> System.out.print(i + " "));
        System.out.println("\ncollect方法加工后的数据：");
        this.result.forEach(i -> System.out.print(i + " "));
    }
}

class Container {
    // 定义本地的result
    public Set<Double> set;

    public Container() {
        this.set = new HashSet<>();
    }

    public Container accumulate(int num) {
        this.set.add(Compute.compute(num));
        return this;
    }

    public Container combine(Container container) {
        this.set.addAll(container.set);
        return this;
    }

    public Set<Double> getResult() {
        return this.set;
    }
}

class Compute {
    public static Double compute(int num) {
        return (double) (2 * num);
    }
}