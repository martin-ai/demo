package com.example.demo.Ai;

import java.math.BigDecimal;

public class DnnlmCnItem {

    private BigDecimal prob;    //句子的切词结果
    private String word;        //该词在句子中的概率值,取值范围[0,1]

    public BigDecimal getProb() {
        return prob;
    }

    public void setProb(BigDecimal prob) {
        this.prob = prob;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
