package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class NormalTest {

    @Test
    public void test() {
        int[] ints = new int[]{4, 3, 2, 1, 5, 6, 7, 8};
        System.out.println(String.format("原始值:%s", JSONObject.toJSONString(ints)));
        borderSorted(ints);
    }

    private void borderSorted2(int[] ints) {
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

    private void borderSorted(int[] ints) {
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
