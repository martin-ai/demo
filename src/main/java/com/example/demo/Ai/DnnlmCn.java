package com.example.demo.Ai;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

//DNN语言模型
public class DnnlmCn {

    @Id
    private String logId;
    private String text;        //文本内容（GBK编码），最大512字节，不需要切词
    private List<DnnlmCnItem> items;
    private BigDecimal ppl;     //描述句子通顺的值：数值越低，句子越通顺
    @CreatedDate
    @LastModifiedDate
    Instant timestamp;
    private
    @Version
    Long version;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DnnlmCnItem> getItems() {
        return items;
    }

    public void setItems(List<DnnlmCnItem> items) {
        this.items = items;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BigDecimal getPpl() {
        return ppl;
    }

    public void setPpl(BigDecimal ppl) {
        this.ppl = ppl;
    }

}
