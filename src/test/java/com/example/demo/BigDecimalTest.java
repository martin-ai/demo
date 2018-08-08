package com.example.demo;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 初始化一个java.math.BigDecimal时尽量用java.math.BigDecimal.valueOf(0)来替代new java.math.BigDecimal()．
 * 比较两个java.math.BigDecimal对象时，最好用equals(),而不要进行数值转换，如：doubleValue等,测试发现凡涉及到类型转换，性能都要受到较大影响。
 * <p>
 * 测试感觉时间差不多 没必要改
 **/
public class BigDecimalTest {

    final private int max = 10;

    /**
     * 测试1：测试BigDecimal初始化
     * 不能这样测试 后者永远比前者快 应该分开测试
     * BigDecimal new :128 milliseconds
     * BigDecimal valueOf :4 milliseconds
     **/
    @Test
    public void testCreateBigDecimal() {
        testDouble();
        testBigDecimal();
    }

    /**
     * 分开测试 new BigDecimal 反而还快点
     * BigDecimal new :132 milliseconds
     * BigDecimal valueOf :143 milliseconds
     * <p>
     * BigDecimal new :103 milliseconds
     * BigDecimal valueOf :137 milliseconds
     **/
    @Test
    public void testNew() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        IntStream.rangeClosed(0, max).forEach(x -> {
            BigDecimal b = new BigDecimal(x);
        });
        stopwatch.stop();
        System.out.println(String.format("BigDecimal new :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void testValueOf() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        IntStream.rangeClosed(0, max).forEach(x -> {
            BigDecimal b = BigDecimal.valueOf(x);
        });
        stopwatch.stop();
        System.out.println(String.format("BigDecimal valueOf :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 测试2：测试BigDecimal & Double 计算消耗时间
     * 感觉double计算比bigDecimal也快不了多少
     * test calc double :130 milliseconds
     * test calc bigDecimal :119 milliseconds
     * <p>
     * test calc double :119 milliseconds
     * test calc bigDecimal :156 milliseconds
     * <p>
     * test calc double :100 milliseconds
     * test calc bigDecimal :138 milliseconds
     **/
    @Test
    public void testDouble() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        IntStream.rangeClosed(1, max).forEach(x -> {
            double d = (double) x;
            double result = (d / d + 1) * d;
        });
        stopwatch.stop();
        System.out.println(String.format("test calc double :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void testBigDecimal() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        IntStream.rangeClosed(1, max).forEach(x -> {
            BigDecimal b = new BigDecimal(x);
            BigDecimal result = b.divide(b, 2, BigDecimal.ROUND_CEILING).add(BigDecimal.ONE).multiply(b);
        });
        stopwatch.stop();
        System.out.println(String.format("test calc bigDecimal :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 测试3：测试BigDecimal转换类型
     * test with convent :68 milliseconds
     * test without convent :80 milliseconds
     * <p>
     * test with convent :76 milliseconds
     * test without convent :58 milliseconds
     **/
    @Test
    public void testConvert() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Double> s = IntStream.rangeClosed(1, max).mapToObj(x -> {
            BigDecimal b = new BigDecimal(x);
            BigDecimal result = b.divide(b, 2, BigDecimal.ROUND_CEILING).add(BigDecimal.ONE).multiply(b);
            return result.doubleValue();
        }).collect(Collectors.toList());
        stopwatch.stop();
        System.out.println(String.format("test with convent :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void testConvert2() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<BigDecimal> s = IntStream.rangeClosed(1, max).mapToObj(x -> {
            BigDecimal b = new BigDecimal(x);
            BigDecimal result = b.divide(b, 2, BigDecimal.ROUND_CEILING).add(BigDecimal.ONE).multiply(b);
            return result.setScale(0, BigDecimal.ROUND_CEILING);
        }).collect(Collectors.toList());
        stopwatch.stop();
        System.out.println(String.format("test without convent :%d milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }


}