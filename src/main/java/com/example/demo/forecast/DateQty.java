package com.example.demo.forecast;

import java.math.BigDecimal;

public class DateQty {

    private int date;
    private BigDecimal qty;

    public DateQty() {
    }

    public DateQty(int date, BigDecimal qty) {
        this.date = date;
        this.qty = qty;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

}
