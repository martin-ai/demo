package com.example.demo;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 比较两个java.math.BigDecimal对象时，最好用equals(),而不要进行数值转换，如：doubleValue等,测试发现凡涉及到类型转换，性能都要受到较大影响。
 **/
public class BigDecimalTest {

    final private int max = 5000;

    /**
     * 测试1：测试BigDecimal & Double 计算消耗时间
     * test calc double :10 milliseconds
     * test calc bigDecimal :6056 milliseconds
     * StopWatch 'test calc : 100000000': running time (millis) = 6094
     * -----------------------------------------
     * ms     %     Task name
     * -----------------------------------------
     * 00035  001%  calc double
     * 06059  099%  calc bigDecimal
     * <p>
     * test calc double :7 milliseconds
     * test calc bigDecimal :2781 milliseconds
     * StopWatch 'test calc : 25000000': running time (millis) = 2820
     * -----------------------------------------
     * ms     %     Task name
     * -----------------------------------------
     * 00038  001%  calc double
     * 02782  099%  calc bigDecimal
     * <p>
     * 结果 BigDecimal 比 Double 效率低很多，但double会丢失精度， 用时间来换精度 值！
     **/
    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch(String.format("test calc : %d", max * max));
        stopWatch.start("calc double");
        testDoubleCalc();
        stopWatch.stop();
        stopWatch.start("calc bigDecimal");
        testBigDecimalCalc();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public void testDoubleCalc() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        double result = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                result += i * j;
            }
        }
        stopwatch.stop();
        System.out.println(String.format("test calc double :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    public void testBigDecimalCalc() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        BigDecimal result = BigDecimal.valueOf(0);
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                BigDecimal decimalA = BigDecimal.valueOf(i);
                BigDecimal decimalB = BigDecimal.valueOf(j);
                result = result.add(decimalA.multiply(decimalB));
            }
        }
        stopwatch.stop();
        System.out.println(String.format("test calc bigDecimal :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 测试2：验证 初始化一个java.math.BigDecimal时尽量用java.math.BigDecimal.valueOf(0)来替代new java.math.BigDecimal()．
     * test bigDecimal constructor :66 milliseconds
     * test bigDecimal valueOf :304 milliseconds
     * StopWatch 'test constructor : 100000000': running time (millis) = 397
     * -----------------------------------------
     * ms     %     Task name
     * -----------------------------------------
     * 00092  023%  bigDecimal constructor
     * 00305  077%  bigDecimal valueOf
     * <p>
     * test bigDecimal constructor :45 milliseconds
     * test bigDecimal valueOf :88 milliseconds
     * StopWatch 'test constructor : 25000000': running time (millis) = 137
     * -----------------------------------------
     * ms     %     Task name
     * -----------------------------------------
     * 00047  034%  bigDecimal constructor
     * 00090  066%  bigDecimal valueOf
     * <p>
     * 结果 BigDecimal.valueOf()比new BigDecimal()慢．推翻
     **/
    @Test
    public void test2() {
        StopWatch stopWatch = new StopWatch(String.format("test constructor : %d", max * max));

        stopWatch.start("bigDecimal constructor");
        testBigDecimalConstructor();
        stopWatch.stop();

        stopWatch.start("bigDecimal valueOf");
        testBigDecimalValueOf();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public void testBigDecimalConstructor() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                BigDecimal a = new BigDecimal(i * j);
            }
        }
        stopwatch.stop();
        System.out.println(String.format("test bigDecimal constructor :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    public void testBigDecimalValueOf() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                BigDecimal a = BigDecimal.valueOf(i * j);
            }
        }
        stopwatch.stop();
        System.out.println(String.format("test bigDecimal valueOf :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 源码：
     * public static BigDecimal valueOf(double val) {
     * return new BigDecimal(Double.toString(val));
     * }
     * <p>
     * 总结：
     * 1.BigDecimal构造方法double会丢失精度 不建议用 用BigDecimal.valueOf(double val)代替
     * 2.BigDecimal.valueOf()不会丢失精度 但是当传入值为float时 转double会丢失精度 故不建议传float
     * 3.BigDecimal.valueOf(double val) 等价与 new BigDecimal(String str)
     **/
    @Test
    public void test3() {
        System.out.println(new BigDecimal("0.03"));         //public BigDecimal(String val) //0.03
        System.out.println(new BigDecimal(3L));             //public BigDecimal(long val)   //3
        System.out.println(new BigDecimal(3d));             //public BigDecimal(double val) //3
        //BigDecimal(double val)会丢失精度
        System.out.println(new BigDecimal(0.03d));          //public BigDecimal(double val) //0.0299999999999999988897769753748434595763683319091796875
        System.out.println(new BigDecimal(0.03f));          //public BigDecimal(double val) //0.02999999932944774627685546875

        System.out.println(BigDecimal.valueOf(3L));         //public static BigDecimal valueOf(long val)   //3
        System.out.println(BigDecimal.valueOf(0.03d));      //public static BigDecimal valueOf(double val) //0.03
        //float -> double丢失精度
        System.out.println(BigDecimal.valueOf(0.03f));      //public static BigDecimal valueOf(double val) //0.029999999329447746

        System.out.println(new BigDecimal(3d));

    }

}