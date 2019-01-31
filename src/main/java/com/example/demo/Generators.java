package com.example.demo;

import java.util.ArrayList;
import java.util.Random;

public class Generators {

    final static int _capacity = 10000;
    final static Random rand = new Random(System.currentTimeMillis() + _capacity);
    static ArrayList<Integer> list = new ArrayList<Integer>(_capacity);

    public static ArrayList<Integer> generateTestData() {
        long ts = System.currentTimeMillis();
        int modVal = _capacity / 3;
        for (int i = 0; i < _capacity; i++) {
            rand.setSeed(i);
            list.add(Math.abs(rand.nextInt() % modVal));
        }
        ts = System.currentTimeMillis() - ts;
        System.out.println("generate test data time ：" + ts);
        return list;
    }

}
