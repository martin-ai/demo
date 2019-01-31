package com.example.demo;

import com.example.demo.stream.StreamService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StreamTest extends AiDemoApplicationTests {

    @Autowired
    private StreamService streamService;

    /**
     * generate test data time ：26428 (数量级为50000000 占用内存~1G)
     * 1.6666665E7
     * handle without lambda cost time ：38288
     * 1.6666665E7
     * handle within lambda cost time ：8717
     * 1.6666665E7
     * handle within lambda cost time ：504
     * <p>
     * generate test data time ：7 (数量级为10000)
     * 3332.000600240042
     * handle without lambda cost time ：19
     * 3332.000600240042
     * handle within lambda cost time ：14
     * 3332.000600240042
     * handle within lambda cost time ：12
     */
    @Test
    public void test1() {
        List<Integer> testDataList = Generators.generateTestData();
        streamService.handleWithoutLambda(testDataList);
        streamService.handleWithinLambda(testDataList);
        streamService.handleWithinParallelLambda(testDataList);
    }

    /**
     * 串行执行的大小：10000
     * 并行执行的大小：9803
     * 加锁并行执行的大小：10000
     * <p>
     * 总结就是paralleStream里直接去修改变量是非线程安全的，但是采用collect和reduce操作就是满足线程安全的了
     * <p>
     * Java8的paralleStream用fork/join框架
     * fork/join 简单地说就是大任务拆分成小任务，分别用不同线程去完成，然后把结果合并后返回。
     * 所以第一步是拆分，第二步是分开运算，第三步是合并。
     * 这三个步骤分别对应的就是Collector的supplier,accumulator和combiner
     */
    @Test
    public void test2() {
        streamService.test();
        streamService.run();

    }

}
