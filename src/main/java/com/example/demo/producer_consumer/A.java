package com.example.demo.producer_consumer;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.util.UUID;

public class A {

    @Id
    private String id;
    private String name;
    @CreatedDate
    @LastModifiedDate
    Instant timestamp;
    private
    @Version
    Long version;

    public A(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Long getVersion() {
        return version;
    }

}
