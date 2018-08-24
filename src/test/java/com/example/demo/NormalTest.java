package com.example.demo;

import com.example.demo.date.MyDateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.List;
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
    public void testApacheCollectionUtils() {
        List<String> stringList1 = Lists.newArrayList("1", "2");
        List<String> stringList2 = Lists.newArrayList("1", "3");
        List<String> stringList3 = Lists.newArrayList("1");
        System.out.println(CollectionUtils.containsAny(stringList1, stringList2));
        System.out.println(CollectionUtils.containsAny(stringList1, stringList3));
        System.out.println(CollectionUtils.containsAll(stringList1, stringList2));
        System.out.println(CollectionUtils.containsAll(stringList1, stringList3));
    }

    @Test
    public void testDateUtils() {
        int max = 1000000;
        Date date = new Date();
        String dateStr = DateFormatUtils.format(date, "yyyyMM");
//        IntStream.rangeClosed(-12, 12).forEach(num -> {
//            int s = DataUtils.addMonth(Integer.valueOf(dateStr), num);
//            System.out.printf("%d--%d\n", num, s);
//        });

        StopWatch stopWatch = new StopWatch("test calc date");
        stopWatch.start("date add month");
        IntStream.rangeClosed(-max, max).forEach(num -> {
            DateUtils.addMonths(date, num);
        });
        stopWatch.stop();
        stopWatch.start("int add month");
        IntStream.rangeClosed(-max, max).forEach(num -> {
            int dateInt = Integer.valueOf(dateStr);
            MyDateUtils.addMonth(dateInt, num);
        });
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testEnum() {
        System.out.println("----------- Meal ------------");
        for (Meal meal : Meal.values()) {
            System.out.printf("%d -------------- %s\n", meal.ordinal() + 1, meal.name());
            for (Meal.Food food : meal.getValues()) {
                System.out.printf("\t%d ------ %s $%.2f\n", food.getSort() + 1, food.getName(), food.getPrice());
            }
        }
    }

    @Test
    public void testEnum2() {
        WeekEnum weekEnum = WeekEnum.valueOf("Monday"); //WeekEnum weekEnum = WeekEnum.valueOf("Mon"); java.lang.IllegalArgumentException: No enum constant com.example.demo.WeekEnum.Mon
        print(weekEnum);
        weekEnum = WeekEnum.getByCodeOrName("星期一");
        print(weekEnum);
    }

    @Test
    /**
     * 测试枚举比较,使用equal和==
     *
     * 使用== 和使用equals方法的执行结果是一样的。
     * 实际上是因为枚举的赋值根本不是 new 出来的. 都是直接使用了地址, 而枚举元素本身的构造函数又被私有化了(不允许public, 如果 public 会报错).
     * 所以在外面根本就不允许 new 一个枚举元素出来. 看下面的代码:
     * WeekEnum s1 = WeekEnum.Friday; WeekEnum s2 = WeekEnum.Friday;
     * 根本就不是 new 一个新对象嘛, s1 和 s2 都指向 WeekEnum.Friday 同一个元素, 当然地址一样了.
     **/
    public void testEnum3() {
        WeekEnum s1 = WeekEnum.FRIDAY;
        WeekEnum s2 = WeekEnum.FRIDAY;
        WeekEnum ss1 = WeekEnum.MONDAY;

        System.out.println("s1 == s2：" + (s1 == s2));
        System.out.println("s1.equals(s2)：" + (s1.equals(s2)));

        System.out.println("s1 == ss1：" + (s1 == ss1));
        System.out.println("s1.equals(ss1)：" + (s1.equals(ss1)));
    }

    private void print(Enum e) {
        if (e == null) {
            return;
        }
        System.out.printf("%d-%s\n", e.ordinal(), e.name());
    }

}
