package com.example.demo.Ai;

import com.baidu.aip.nlp.AipNlp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.URL;

@Configuration
public class AiConfig {

    @Value("${ai.config.app-id}")
    private String appId;
    @Value("${ai.config.client-id}")
    private String clientId;
    @Value("${ai.config.client-secret}")
    private String clientSecret;

    @Bean
    public AipNlp aipNlp() {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(appId, clientId, clientSecret);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        URL s = getClass().getClassLoader().getResource("ai-baidu/log4j.properties");
        System.setProperty("aip.log4j.conf", getClass().getClassLoader().getResource("ai-baidu/log4j.properties").getPath());
        return client;
    }

}
