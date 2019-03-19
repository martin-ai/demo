package com.example.demo;

import com.example.demo.stream.StreamService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    /*
     * 涉及知识
     * 1.BiFunction它是一个函数式接口，包含的函数式方法定义如下：R apply(T t, U u);
     * 它与Function不同点在于它接收两个输入返回一个输出； 而Function接收一个输入返回一个输出，它的两个输入、一个输出的类型可以是不一样的
     * 2.BinaryOperator它的定义：public interface BinaryOperator<T> extends BiFunction<T,T,T>
     * BinaryOperator就直接限定了其三个参数必须是一样的
     * 3.BiConsumer包含的函数式方法定义如下：void accept(T t, U u);
     * 它就是一个两个输入参数的Consumer的变种。计算没有返回值。
     *
     * Reduce中文含义为：减少、缩小；而Stream中的Reduce方法干的正是这样的活：根据一定的规则将Stream中的元素进行计算后返回一个唯一的值。
     * 一个参数的Reduce定义如下：Optional<T> reduce(BinaryOperator<T> accumulator)
     * 在求和、求最大最小值等方面都可以很方便的实现
     *
     * 两个参数的Reduce定义如下：T reduce(T identity, BinaryOperator<T> accumulator)
     * 它与一参数时的应用场景类似，不同点是它使用在可能需要某些初始化值的场景中
     *
     * 三个参数的Reduce定义如下：<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
     * identity: 一个初始化的值；这个初始化的值其类型是泛型U，与Reduce方法返回的类型一致；
     * 注意此时Stream中元素的类型是T，与U可以不一样也可以一样，这样的话操作空间就大了；
     * 不管Stream中存储的元素是什么类型，U都可以是任何类型，如U可以是一些基本数据类型的包装类型Integer、Long等；或者是String，又或者是一些集合类型ArrayList等；
     * accumulator: 其类型是BiFunction，输入是U与T两个类型的数据，而返回的是U类型；
     * 也就是说返回的类型与输入的第一个参数类型是一样的，而输入的第二个参数类型与Stream中元素类型是一样的。
     * combiner: 其类型是BinaryOperator，支持的是对U类型的对象进行操作；
     * 第三个参数combiner主要是使用在并行计算的场景下；如果Stream是非并行时，第三个参数实际上是不生效的。
     *
     * collect含义与Reduce有点相似,定义如下：<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
     * supplier：动态的提供初始化的值；创建一个可变的结果容器（JAVADOC）；对于并行计算，这个方法可能被调用多次，每次返回一个新的对象；
     * accumulator：类型为BiConsumer，注意这个接口是没有返回值的；它必须将一个元素放入结果容器中（JAVADOC）。
     * combiner：类型也是BiConsumer，因此也没有返回值。它与三参数的Reduce类型，只是在并行计算时汇总不同线程计算的结果。它的输入是两个结果容器，必须将第二个结果容器中的值全部放入第一个结果容器中（JAVADOC）。
     *
     * */
    @Test
    public void test3() {
        //test stream reduce
        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
        System.out.println(s.reduce("[value]", new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                return s.concat(s2);
            }
        }));
        //等价于
        //System.out.println(s.reduce("[value]", (s1, s2) -> s1.concat(s2)));
        //等价于
        //System.out.println(s.reduce("[value]", String::concat));

        //test stream collect
        //不能继续使用第一个stream 在执行reduce后stream被关闭 java.lang.IllegalStateException: stream has already been operated upon or closed
        Stream<String> ss = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
        //每个线程都创建了一个结果容器supplier；假设4个线程，那么accumulator处理的结果将会是四个结果容器（ArrayList）；最终再调用第三个BiConsumer参数将结果全部Put到第一个List中
        System.out.println(
                ss.parallel().collect(
                        (Supplier<ArrayList<String>>) ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                )
        );
    }

    static volatile int num = 1;

    @Test
    //输出结果为（4+1）+（4+2）+（4+3）= 18 故reduce线程不可见 会有问题
    public void test4() {
        System.out.println(Stream.of(1, 2, 3).parallel().reduce(
                4,
                new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        System.out.println(String.format("%d:%d", integer, integer2));
                        return integer + integer2;
                    }
                },
                new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                }));
    }

}
