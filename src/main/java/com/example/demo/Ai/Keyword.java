package com.example.demo.Ai;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.util.List;

public class Keyword {

    @Id
    private String logId;
    private List<KeywordItem> items;
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

    public List<KeywordItem> getItems() {
        return items;
    }

    public void setItems(List<KeywordItem> items) {
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

}
