package com.example.demo.Tools;

public class MyMathsUtils {

    private static final int DEFAULT_MAX_THRESHOLD = Integer.MAX_VALUE;

    /**
     * 作用：找到比输入大的，并且与输入相邻的2的次方数
     * 限制：只用于正整数
     * 解析：int 4字节 -2的31次方~2的31次方 -2147483648~2147483647
     * 故 >>>16 完全能处理所有Int
     */
    public static int findNextPowerNum(int cap) {
        return findNextPowerNum(cap, DEFAULT_MAX_THRESHOLD);
    }

    public static int findNextPowerNum(int cap, int maxThreshold) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= maxThreshold) ? maxThreshold : n + 1;
    }

}
