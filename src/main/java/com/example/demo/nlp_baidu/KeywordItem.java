package com.example.demo.nlp_baidu;

import com.alibaba.fastjson.annotation.JSONField;

public class KeywordItem {

    private int sentiment;
    private String adj;
    private String prop;
    @JSONField(name = "abstract")
    private String abstract_text;
    private int beginPos;
    private int endPos;

    public int getSentiment() {
        return sentiment;
    }

    public void setSentiment(int sentiment) {
        this.sentiment = sentiment;
    }

    public String getAdj() {
        return adj;
    }

    public void setAdj(String adj) {
        this.adj = adj;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getAbstract_text() {
        return abstract_text;
    }

    public void setAbstract_text(String abstract_text) {
        this.abstract_text = abstract_text;
    }

    public int getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(int beginPos) {
        this.beginPos = beginPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

}
