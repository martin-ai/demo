package com.example.demo;

import com.google.common.collect.Maps;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService {

    @Cacheable(value = "cacheTest")
    public Map<String, String> getStr() {
        System.out.println("test-cache-able");
        Map<String, String> map = Maps.newHashMap();
        map.put("ss", "ddd");
        return map;
    }

    @CachePut(value = "cacheTest")
    public Map<String, String> updateStr() {
        System.out.println("cache-put");
        Map<String, String> map = Maps.newHashMap();
        map.put("ss", "ddeeee");
        return map;
    }

    @CacheEvict(value = {"cacheTest"}, allEntries = true)
    public void clean() {
        System.out.println("cache-evict");
    }

}
