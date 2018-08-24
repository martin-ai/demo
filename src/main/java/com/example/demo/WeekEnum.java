package com.example.demo;

/**
 * 逻辑代码里面是不应该出现常量字符串和常量数字之类的东西。
 * 这些东西都应该被定义成一个常量，然后再在其他地方使用。
 * 不然在很久之后，忽然有些地方的值换了，只需要修改一处地方，整个项目都不用担心会出问题，
 * 但是，如果你没有这么干，那么，没人知道你在逻辑代码里面还有这样的常量存在。
 * 那么代码就会出现美妙的后果，然后就炸了。
 * <p>
 * enum命名规则:
 * 枚举类名建议带上Enum后缀，枚举成员名称需要全大写，单词间用下划线隔开
 * 说明：枚举其实就是特殊的常量类，且构造方法被默认强制私有
 * 正例：枚举名字：DealStatusEnum；成员名称：SUCCESS/UNKNOWN_REASON
 * <p>
 * enum常用方法：
 * valueOf(String name)方法：返回带指定名称的指定枚举类型的枚举常量。
 * ordinal()方法：从枚举类型的第一个枚举开始，依次从零开始往上递增。
 * values()方法：返回的是一个类型与枚举类型一致的数组。
 * compareTo()方法: 其内部是通过ordinal()值比较的
 * <p>
 * enum的好处以及与常量类的区别:
 * 1.枚举型可以直接与数据库打交道，我通常使用varchar类型存储，对应的是枚举的常量名。
 * 2.switch语句支持枚举型，当switch使用int、String类型时，由于值的不稳定性往往会有越界的现象，
 * 对于这个的处理往往只能通过if条件筛选以及default模块来处理。而使用枚举型后，在编译期间限定类型，不允许发生越界的情况
 * 3.当你使用常量类时，往往得通过equals去判断两者是否相等，使用枚举的话由于常量值地址唯一，可以用==直接对比，性能会有提高
 * 4.常量类编译时，是直接把常量的值编译到类的二进制代码里，常量的值在升级中变化后，需要重新编译引用常量的类，因为里面存的是旧值。
 * 枚举类编译时，没有把常量值编译到代码里，即使常量的值发生变化，也不会影响引用常量的类。
 * 5.枚举类编译后默认为final class，不允许继承可防止被子类修改。常量类可被继承修改、增加字段等，容易导致父类的不兼容。
 */
public enum WeekEnum {

    MONDAY("Mon", "星期一"),
    TUESDAY("Tues", "星期二"),
    WEDNESDAY("Wed", "星期三"),
    THURSDAY("Thur", "星期四"),
    FRIDAY("Fri", "星期五"),
    SATURDAY("Sat", "星期六"),
    SUNDAY("Sun", "星期天");

    private final String code;
    private final String name;

    WeekEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static WeekEnum getByCodeOrName(String param) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            if (weekEnum.getCode().equalsIgnoreCase(param) || weekEnum.getName().equalsIgnoreCase(param)) {
                return weekEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
