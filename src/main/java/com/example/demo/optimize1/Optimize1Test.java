package com.example.demo.optimize1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Optimize1Test {

    final static int _capacity = 1000000;
    final static Random rand = new Random(System.currentTimeMillis() + _capacity);
    static ArrayList<String> list = new ArrayList<String>(_capacity);
    static ArrayList<String> newlist = new ArrayList<String>(_capacity);

    public static void main(String[] args) {
        generateTestData();
        beforeOptimize();
        afterOptimize();
    }

    private static void generateTestData() {
        long ts = System.currentTimeMillis();
        int modVal = _capacity / 3;
        for (int i = 0; i < _capacity; i++) {
            rand.setSeed(i);
            list.add(Integer.toString(Math.abs(rand.nextInt() % modVal)));
        }
        ts = System.currentTimeMillis() - ts;
        System.out.println("generate test data time ：" + ts);
    }

    private static void beforeOptimize() {
        newlist.clear();
        int repetition = 0;
        long ts = System.currentTimeMillis();
        for (String s : list) {
            if (!newlist.contains(s)) {
                newlist.add(s);
            } else {
                repetition++;
            }
        }
        ts = System.currentTimeMillis() - ts;
        System.out.println("----------- before optimize -----------");
        System.out.println("find time ：" + ts);
        System.out.println("repetition : " + repetition);
        System.out.println("correction : " + newlist.size());
    }

    private static void afterOptimize() {
        newlist.clear();
        int repetition = 0;
        long ts = System.currentTimeMillis();
        Collections.sort(list);
        String str = list.get(0);
        for (int i = 1; i < _capacity; i++) {
            if (str.equals(list.get(i))) {
                repetition++;
                continue;
            }
            newlist.add(str);
            str = list.get(i);
        }
        newlist.add(str);
        ts = System.currentTimeMillis() - ts;
        System.out.println("----------- after optimize -----------");
        System.out.println("find time ：" + ts);
        System.out.println("repetition : " + repetition);
        System.out.println("correction : " + newlist.size());
    }

}
