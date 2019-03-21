package com.example.demo.baidunlp;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestUtils {

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject requestPost(String url, Object... param) {
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, null, Map.class, param);
        return new JSONObject(responseEntity.getBody());
    }


    public JSONObject requestPost(String url, Map<String, String> body, Object... param) {
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Map>(body), Map.class, param);
        return new JSONObject(responseEntity.getBody());
    }

}
