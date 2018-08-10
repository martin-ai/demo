package com.example.demo.Ai;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.*;

import java.net.URI;

@Configuration
public class RestConfiguration {

    private final static Logger LOG = LoggerFactory.getLogger(RestConfiguration.class);

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate() {
            @Override
            protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
                try {
                    LOG.info("Request Url : {}", JSONObject.toJSONString(url));
                    return super.doExecute(url, method, requestCallback, responseExtractor);
                } catch (HttpStatusCodeException e) {
                    LOG.error("Http Status : {} ,Error Message : {}", e.getStatusCode(), e.getMessage());
                    throw e;
                } catch (Exception e) {
                    LOG.error("Request Failed");
                    e.printStackTrace();
                    throw e;
                }
            }
        };
        return restTemplate;
    }

}
