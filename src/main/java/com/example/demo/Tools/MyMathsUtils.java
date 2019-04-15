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

    /**
     * 作用：判断输入的int是否为质数
     * 解析：质数定义为在大于1的自然数中，除了1和它本身以外不再有其他因数。
     * 2,3,5,7,...
     */
    public static boolean isPrimeNum(int num) {
        if (num <= 2) return (num == 2);
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

}
