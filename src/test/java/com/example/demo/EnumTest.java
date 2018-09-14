package com.example.demo;

import org.junit.Test;

public class EnumTest {

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
        WeekEnum weekEnum = WeekEnum.valueOf("MONDAY"); //WeekEnum weekEnum = WeekEnum.valueOf("Mon"); java.lang.IllegalArgumentException: No enum constant com.example.demo.WeekEnum.Mon
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
