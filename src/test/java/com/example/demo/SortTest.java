package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortTest {

    final private boolean isPrint = false;

    @Test
    //测试交换排序 冒泡排序&&快速排序
    public void batchTestSwopSorted() {
        testSorted(100);
//        testSorted(500);
//        testSorted(1000);
//        testSorted(5000);
//        testSorted(10000);
//        testSorted(20000);
//        testSorted(30000);
//        testSorted(40000);
        testSorted(50000);
    }

    public void testSorted(int sortedNum) {
        List<Integer> list = IntStream.range(0, sortedNum).boxed().collect(Collectors.toList());
        Collections.shuffle(list);//费雪耶兹（Fisher–Yates） 也被称作高纳德（ Knuth）随机置乱算法
        int[] ints = list.stream().mapToInt(x -> x).toArray();
        print(String.format("原始值:%s", JSONObject.toJSONString(ints)));
        StopWatch watch = new StopWatch(String.format("test sort num : %d", sortedNum));
        watch.start("test border sort");
        print("border sort");
        borderSorted(ints.clone());
        watch.stop();
        watch.start("test quick sort");
        print("quick sort");
        quickSorted(ints.clone());
        watch.stop();
        print(watch.prettyPrint(), true);
    }

    @Test
    //测试冒泡排序
    public void testBorderSorted() {
//        int[] ints = new int[]{4, 3, 2, 1, 5, 6, 7, 8};
        List<Integer> list = IntStream.range(0, 50).boxed().collect(Collectors.toList());
        Collections.shuffle(list);//费雪耶兹（Fisher–Yates） 也被称作高纳德（ Knuth）随机置乱算法
        int[] ints = list.stream().mapToInt(x -> x).toArray();
        print(String.format("原始值:%s", JSONObject.toJSONString(ints)));
        StopWatch watch = new StopWatch("test border sort");
        watch.start("before improvement");
        print("before improvement");
        borderSortedBeforeImprovement(ints.clone());
        watch.stop();
        watch.start("after improvement");
        print("after improvement");
        borderSortedAfterImprovement(ints.clone());
        watch.stop();
        print(watch.prettyPrint(), true);
    }

    /**
     * 冒泡排序算法最坏情况和平均复杂度是O(n²)。
     * 冒泡排序的空间复杂度为O(1)。
     **/
    public void borderSorted(int[] ints) {
        borderSortedAfterImprovement(ints);
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
            print(String.format("第%d次,查询%d次,结果:%s", (count++), (count2), JSONObject.toJSONString(ints)));
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
            print(String.format("第%d次,查询%d次,结果:%s", (count++), (count2), JSONObject.toJSONString(ints)));
            sortBorder = lastExchangeIndex;
            if (sortedFlag) {
                break;
            }
        }
    }

    @Test
    //测试快速排序
    public void testQuickSorted() {
        int[] ints = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        print(String.format("original:%s", JSONObject.toJSONString(ints)));
        quickSorted(ints);
        print(String.format("result：%s", JSONObject.toJSONString(ints)));
    }

    /**
     * 快速排序就是冒泡排序的一种改进
     * 快速排序是一种快速的分而治之的算法，它是已知的最快的排序算法，其平均运行时间为O(N*1ogN)
     * 当基准数选择的不合理的时候他的效率又会编程O(N*N)。
     * 快速排序的最好情况：
     * 快速排序的最好情况是每次都划分后左右子序列的大小都相等，其运行的时间就为O(N*1ogN)。
     * 快速排序的最坏情况：
     * 快速排序的最坏的情况就是当分组重复生成一个空序列的时候，这时候其运行时间就变为O(N*N)
     * 快速排序的平均情况：
     * 平均情况下是O(N*logN),证明省略。
     * 综上所述，快速排序的时间复杂度为O(N*1ogN),快速排序的空间复杂度为O(1ogN)
     **/
    public void quickSorted(int[] arr) {
        quickSorted(arr, 0, arr.length - 1);
    }

    private void quickSorted(int[] arr, int startIndex, int endIndex) {
        //递归结束条件：startIndex 大于 endIndex
        if (startIndex >= endIndex) {
            return;
        }
        //得到基准元素位置
        int pivotIndex = partition(arr, startIndex, endIndex);
        //用分治法递归数列两部分
        quickSorted(arr, startIndex, pivotIndex - 1);
        quickSorted(arr, pivotIndex + 1, endIndex);
    }

    private int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束
        while (right >= left) {
            //right指针从右向左进行比较
            while (right >= left) {
                if (arr[right] < pivot) {
                    arr[index] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        print(Arrays.toString(arr));
        return index;
    }

    private void print(String str, Boolean isPrint) {
        if (isPrint) {
            System.out.println(str);
        }
    }

    private void print(String str) {
        if (isPrint) {
            System.out.println(str);
        }
    }

//我的实现
//    private int partition(int[] arr, int startIndex, int endIndex) {
//        // 取第一个位置的元素作为基准元素
//        int pivot = arr[startIndex];
//        int pivotIndex = startIndex;
//        //大循环在左右指针重合或者交错时结束
//        while (startIndex < endIndex) {
//            //右路先锋
//            while (endIndex > startIndex) {
//                if (arr[endIndex] < pivot) {
//                    arr[pivotIndex] = arr[endIndex];
//                    pivotIndex = endIndex;
//                    startIndex++;
//                    break;
//                } else {
//                    endIndex--;
//                }
//            }
//            //左路先锋
//            while (startIndex < endIndex) {
//                if (arr[startIndex] > pivot) {
//                    arr[pivotIndex] = arr[startIndex];
//                    pivotIndex = startIndex;
//                    endIndex--;
//                    break;
//                } else {
//                    endIndex++;
//                }
//            }
//        }
//
//        arr[startIndex] = pivot;
//        print(Arrays.toString(arr));
//        return startIndex;
//    }

//失败
//    private int partition(int[] arr, int startIndex, int endIndex) {
//        // 取第一个位置的元素作为基准元素
//        int pivot = arr[startIndex];
//        int pivotIndex = startIndex;
//
//        while (startIndex < endIndex) {
//            //右路先锋
    //太愚蠢 如果pivot是最大值 endIndex一直减下去 必定数组溢出
//            while (arr[endIndex] >= pivot) {
//                endIndex--;
//            }
//            arr[pivotIndex] = arr[endIndex];
//            pivotIndex = endIndex;
//            startIndex++;
//            //左路先锋
//            while (arr[startIndex] <= pivot) {
//                startIndex++;
//            }
//            arr[pivotIndex] = arr[startIndex];
//            pivotIndex = startIndex;
//        }
//
//        arr[startIndex] = pivot;
//        return startIndex;
//    }

}
