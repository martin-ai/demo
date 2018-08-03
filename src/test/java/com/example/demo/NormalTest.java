package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NormalTest {

    @Test
    //测试冒泡排序
    public void testBorderSorted() {
//        int[] ints = new int[]{4, 3, 2, 1, 5, 6, 7, 8};
        List<Integer> list = IntStream.range(0, 50).boxed().collect(Collectors.toList());
        Collections.shuffle(list);//费雪耶兹（Fisher–Yates） 也被称作高纳德（ Knuth）随机置乱算法
        int[] ints = list.stream().mapToInt(x -> x).toArray();
        System.out.println(String.format("原始值:%s", JSONObject.toJSONString(ints)));
        StopWatch watch = new StopWatch("test border sort");
        watch.start("before improvement");
        System.out.println("before improvement");
        borderSortedBeforeImprovement(ints.clone());
        watch.stop();
        watch.start("after improvement");
        System.out.println("after improvement");
        borderSortedAfterImprovement(ints.clone());
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    private void borderSortedBeforeImprovement(int[] ints) {
        int count = 1; //计数器
        int temp; //临时值
        for (int j = 0; j < ints.length; j++) {
            int count2 = 1;
            for (int i = 0; i < ints.length - 1; i++) {
                if (ints[i] > ints[i + 1]) {
                    temp = ints[i];
                    ints[i] = ints[i + 1];
                    ints[i + 1] = temp;
                }
                count2++;
            }
            System.out.println(String.format("第%d次,查询%d次,结果:%s", (count++), (count2), JSONObject.toJSONString(ints)));
        }
    }

    private void borderSortedAfterImprovement(int[] ints) {
        int count = 1; //计数器
        int temp; //临时值
        boolean sortedFlag;  //有序标记
        int lastExchangeIndex = 0; //有序边界
        int sortBorder = ints.length - 1;

        for (int j = 0; j < ints.length; j++) {
            sortedFlag = true;
            int count2 = 1;
            for (int i = 0; i < sortBorder; i++) {
                if (ints[i] > ints[i + 1]) {
                    temp = ints[i];
                    ints[i] = ints[i + 1];
                    ints[i + 1] = temp;
                    sortedFlag = false;
                    lastExchangeIndex = i;
                }
                count2++;
            }
            System.out.println(String.format("第%d次,查询%d次,结果:%s", (count++), (count2), JSONObject.toJSONString(ints)));
            sortBorder = lastExchangeIndex;
            if (sortedFlag) {
                break;
            }
        }
    }
}
