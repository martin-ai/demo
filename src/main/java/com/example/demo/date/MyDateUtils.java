package com.example.demo.date;

import org.springframework.stereotype.Component;

@Component
public class MyDateUtils {

    public static int addMonth(int statDate, int n) {
        int integerPart = n / 12;
        int decimalPart = n % 12;
        statDate = statDate + integerPart * 100;
        int month = statDate % 100 + decimalPart;

        if (month > 12) {
            statDate = statDate + 100;
            statDate = (statDate / 100 * 100) + (month - 12);
        } else if (month <= 0) {
            statDate = statDate - 100;
            statDate = (statDate / 100 * 100) + (12 + month);
        } else {
            statDate = statDate + decimalPart;
        }

        return statDate;
    }

}
