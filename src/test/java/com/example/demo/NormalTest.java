package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Arithmetic.SoundexArithmetic;
import com.example.demo.Arithmetic.TrieTreeArithmetic;
import com.example.demo.Tools.MyMathsUtils;
import com.example.demo.Tools.MyStringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NormalTest {

    @Test
    //区分 形参传值&引用调用
    //1.形参：用来接收调用该方法时传递的参数。只有在被调用的时候才分配内存空间，一旦调用结束，就释放内存空间。因此仅仅在方法内有效。
    //2.实参：传递给被调用方法的值，预先创建并赋予确定值。
    //3.传值调用：传值调用中传递的参数为基本数据类型，参数视为形参。
    //4.引用调用：传引用调用中，如果传递的参数是引用数据类型，参数视为实参。在调用的过程中，将实参的地址传递给了形参，形参上的改变都发生在实参上。
    //5.传递都是单向的，形参不能传递回实参
    public void testCall() {
        System.out.println("总结1：java的基本数据类型是传值调用，对象引用类型是引用调用。");
        int a = 100;
        System.out.println(String.format("形参传值调用：参数a 初始值=%d 地址=%d", a, System.identityHashCode(a)));
        callByValue(a);
        System.out.println(String.format("形参传值调用：参数a 最终值=%d 地址=%d", a, System.identityHashCode(a)));
        System.out.println("总结2：当传值调用时，改变的是形参的值，并没有改变实参的值，实参的值可以传递给形参，但是，这个传递是单向的，形参不能传递回实参。");

        String b = "old";
        System.out.println(String.format("引用调用调用：参数b 初始值=%s 地址=%d", b, System.identityHashCode(b)));
        callByReference1(b);
        System.out.println(String.format("引用调用调用：参数b 最终值=%s 地址=%d", b, System.identityHashCode(b)));
        System.out.println("总结3：当引用调用时，如果参数是对象，无论对对象做了何种操作，都不会改变实参对象的引用。");

        StringBuffer c = new StringBuffer("old");
        System.out.println(String.format("引用调用调用：参数c 初始值=%s 地址=%d", c, System.identityHashCode(c)));
        callByReference2(c);
        System.out.println(String.format("引用调用调用：参数c 最终值=%s 地址=%d", c, System.identityHashCode(c)));
        System.out.println("总结4：当引用调用时，如果参数是对象，改变了对象的内容，就会改变实参对象的内容。");
    }

    private void callByValue(int a) {
        int beforeAddress = System.identityHashCode(a);
        a = a + 1;
        int afterAddress = System.identityHashCode(a);
        System.out.println(String.format("形参传值调用：参数a 修改值=%d 修改前地址=%d 修改后地址=%d", a, beforeAddress, afterAddress));
    }

    private void callByReference1(String b) {
        int beforeAddress = System.identityHashCode(b);
        b = "new";
        int afterAddress = System.identityHashCode(b);
        System.out.println(String.format("引用调用调用：参数b 修改值=%s 修改前地址=%d 修改后地址=%d", b, beforeAddress, afterAddress));
    }

    private void callByReference2(StringBuffer c) {
        int beforeAddress = System.identityHashCode(c);
        c.append("new");
        int afterAddress = System.identityHashCode(c);
        System.out.println(String.format("引用调用调用：参数c 修改值=%s 修改前地址=%d 修改后地址=%d", c, beforeAddress, afterAddress));
    }

    //总结3的面试题
    protected static class Tst {
        public void foo(StringBuilder builder) {
            builder = new StringBuilder("abc");
        }

        public static void main(String[] args) {
            Tst t = new Tst();
            StringBuilder builder = new StringBuilder("def");
            t.foo(builder);
            System.out.println(builder.toString());
        }
    }

    @Test
    public void testList() {
        List<String> testList = Lists.newArrayList("1", "2");
        callByList(testList);
        System.out.println(JSONObject.toJSONString(testList));
    }

    private void callByList(List<String> list) {
        List<String> listCopy = list;
        //实参的地址传递给了形参
        System.out.println("Old Address:" + System.identityHashCode(list));
        list = Lists.newArrayList(); //改变了形参指向的地址
        System.out.println("New Address:" + System.identityHashCode(list));
        list.clear();//传递是单向的，形参不能传递回实参，所以在新地址上发生的一切与原来的无关
        list.add("3");
        //listCopy 指向原地址 在listCopy上发生的一切 就会发生在原来的对象上
        listCopy.add("4");
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
    public void testStringRegex() {
        String txt = "w12".toUpperCase();
        System.out.println(txt.matches("^W\\d$"));
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

    @Test
    public void testStream() {
        List<String> stringList = Lists.newArrayList("01", "01", "02", "03");
        System.out.println(stringList.stream().max(String::compareTo).orElse(null));
    }

    @Test
    public void testStringUtils() {
        String strHis = "01,02";
        String strN1 = "03";
        String strN2 = "02";
        System.out.println(MyStringUtils.append(strHis, strN1));
        System.out.println(MyStringUtils.append(strHis, strN2));
        System.out.println(MyStringUtils.merge(strN1, strN2));
    }

    @Test
    public void testArithmetic() {
        System.out.println(SoundexArithmetic.calc("May"));
        System.out.println(SoundexArithmetic.calc("Mertin"));
    }

    @Test
    public void testArithmetic2() {
        TrieTreeArithmetic trieTreeArithmetic = TrieTreeArithmetic.getInstance();
        System.out.println(trieTreeArithmetic.getKeyWordsByPrefix("共产党"));
    }

    @Test
    public void test() {
        String s = "131313";
        System.out.println(StringUtils.replaceAll(s, "[:digit:]{3}", ""));
    }

    @Test
    public void testMathsUtils() {
        int powerNum = MyMathsUtils.findNextPowerNum(7);
        System.out.println(powerNum);
    }

}
