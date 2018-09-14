package com.example.demo;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NormalTest {

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

    @Test
    public void testLambda() {
//Error:(146, 13) java: local variables referenced from a lambda expression must be final or effectively final
//        int count = 1;
//        IntStream.rangeClosed(0, 10).forEach(x -> {
//            count++;
//        });
        final int[] count = {0};
        IntStream.range(0, 10).forEach(x -> {
            count[0]++;
        });
        System.out.println(count[0]);
    }

    @Test
    public void testString() {
        String s = String.join(",", IntStream.range(0, 4).mapToObj(x -> "?").collect(Collectors.toList()));
        System.out.println(s);
    }

    @Test
    public void testPage() {
        long totalRecord = 25;
        long pageSize = 10;
        long totalPage = totalRecord / pageSize;

        for (long pageNum = 0; pageNum <= totalPage; pageNum++) {
            System.out.println(String.format("start page : %d", pageNum * pageSize));
            System.out.println(String.format("end page : %d", (pageNum + 1) * pageSize - 1));
        }
    }

}
