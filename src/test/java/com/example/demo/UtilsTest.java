package com.example.demo;

import com.example.demo.Tools.MyDateUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.demo.Tools.MyMathsUtils.isPrimeNum;

public class UtilsTest {

    @Test
    public void testStringUtils() {
        String str = "china";
        //substring则从起始位置开始截取到结束位置（但不包含结束位置)
        //正数从左开始数 负数从右边开始数
        System.out.println(StringUtils.substring(str, 2, 4));       //in
        System.out.println(StringUtils.substring(str, -2, 4));      //n
        System.out.println(StringUtils.substring(str, 2, 10));      //ina
        System.out.println(StringUtils.substring(null, 1, 2));      //null
        System.out.println(StringUtils.substring("", 1, 2));        //""
        System.out.println(StringUtils.substring(str, 10, 2));      //""

        str = "china china";
        System.out.println(StringUtils.substringBefore(str, "i"));  // ch 从"i"第一次出现的位置向前截取
        System.out.println(StringUtils.substringAfter(str, "i"));    //na china 从"i"第一次出现的位置向后截取
        System.out.println(StringUtils.substringBeforeLast(str, "i"));   //china ch 从"i"最后一次出现的位置向前截取
        System.out.println(StringUtils.substringAfterLast(str, "i"));    //na 从"i"最后一次出现的位置向后截取

        str = "p00 01_1.jpg";
        System.out.println(StringUtils.containsNone(str, '_', '.'));    //false 判断字符串中是否不包含指定的字符或指定的字符串中的字符，区分大小写 str为空串总是返回true
        System.out.println(StringUtils.containsAny(str, '_', '.'));     //true  判断字符串中是否包含指定字符集合中或指定字符串中任一字符，区分大小写
        System.out.println(StringUtils.containsOnly(str, '_', '.'));    //false 判断字符串中的字符是否都是出自所指定的字符数组或字符串，区分大小写
        System.out.println(StringUtils.containsWhitespace(str));        //true  判断字符串中的字符是否有空格

        String str1 = "aaa_ss.ss"; //true
        String str2 = "aaass.ss";
        String str3 = "aaa_ssss";
        String str4 = "aaassss";
        System.out.println(StringUtils.contains(str1, ".") && StringUtils.contains(str1, "_"));
        System.out.println(StringUtils.contains(str2, ".") && StringUtils.contains(str2, "_"));
        System.out.println(StringUtils.contains(str3, ".") && StringUtils.contains(str3, "_"));
        System.out.println(StringUtils.contains(str4, ".") && StringUtils.contains(str4, "_"));
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
    public void testApacheDateUtil() {
        Date maxDate = DateUtil.parseYYYYMMDDDate("9999/12/31");
        System.out.println(maxDate);
        try {
            Date errorDate = DateUtil.parseYYYYMMDDDate("9999/13/31");
            System.out.println(errorDate);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        Double day = DateUtil.convertTime("23:23:12");
        System.out.println(day);
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

        stopWatch.start("int add month");
        IntStream.rangeClosed(-max, max).forEach(num -> {
            int dateInt = Integer.valueOf(dateStr);
            MyDateUtils.addMonth(dateInt, num);
        });
        stopWatch.stop();

        stopWatch.start("date add month");
        IntStream.rangeClosed(-max, max).forEach(num -> {
            DateUtils.addMonths(date, num);
        });
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void testMath() {
        int sum = 707829217;
        for (int i = 3; i < Math.sqrt(sum); i++) {
            if (isPrimeNum(i)) {
                if (sum % i == 0) {
                    int s = sum / i;
                    if (isPrimeNum(s)) {
                        System.out.println(String.format("%d,%d", i, s));
                    }
                }
            }
        }
    }

}
