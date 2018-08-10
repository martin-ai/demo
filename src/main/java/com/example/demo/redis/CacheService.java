package com.example.demo.redis;

import com.google.common.collect.Maps;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * SpringBoot + Redis
 * 需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的
 **/
@Service
public class CacheService {

    @Cacheable(value = "cacheTest", key = "#ss")
    public Map<String, String> getStr(String ss) {
        System.out.println("test-cache-able");
        Map<String, String> map = Maps.newHashMap();
        map.put("ss", "ddd");
        return map;
    }

    @CachePut(value = "cacheTest", key = "#ss")
    public Map<String, String> updateStr(String ss) {
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
