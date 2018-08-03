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

    @Test
    //区分 形参传值&引用调用
    //1.形参：用来接收调用该方法时传递的参数。只有在被调用的时候才分配内存空间，一旦调用结束，就释放内存空间。因此仅仅在方法内有效。
    //2.实参：传递给被调用方法的值，预先创建并赋予确定值。
    //3.传值调用：传值调用中传递的参数为基本数据类型，参数视为形参。
    //4.引用调用：传引用调用中，如果传递的参数是引用数据类型，参数视为实参。在调用的过程中，将实参的地址传递给了形参，形参上的改变都发生在实参上。
    public void testCall() {
        System.out.println("总结1：java的基本数据类型是传值调用，对象引用类型是传引用。");
        int a = 100;
        System.out.println(String.format("形参传值调用：参数a 初始值=%d", a));
        callByValue(a);
        System.out.println(String.format("形参传值调用：参数a 最终值=%d", a));
        System.out.println("总结2：当传值调用时，改变的是形参的值，并没有改变实参的值，实参的值可以传递给形参，但是，这个传递是单向的，形参不能传递回实参。");

        String b = "old";
        System.out.println(String.format("引用调用调用：参数b 初始值=%s", b));
        callByReference1(b);
        System.out.println(String.format("引用调用调用：参数b 最终值=%s", b));
        System.out.println("总结3：当引用调用时，如果参数是对象，无论对对象做了何种操作，都不会改变实参对象的引用。");
        StringBuffer c = new StringBuffer("old");
        System.out.println(String.format("引用调用调用：参数c 初始值=%s", c));
        callByReference2(c);
        System.out.println(String.format("引用调用调用：参数c 最终值=%s", c));
        System.out.println("总结4：当引用调用时，如果参数是对象，改变了对象的内容，就会改变实参对象的内容。");
    }

    private void callByValue(int a) {
        a = a + 1;
        System.out.println(String.format("形参传值调用：参数a 修改值=%d", a));
    }

    private void callByReference1(String b) {
        b = "new";
        System.out.println(String.format("引用调用调用：参数b 修改值=%s", b));
    }

    private void callByReference2(StringBuffer c) {
        c.append("new");
        System.out.println(String.format("引用调用调用：参数c 修改值=%s", c));
    }

}
