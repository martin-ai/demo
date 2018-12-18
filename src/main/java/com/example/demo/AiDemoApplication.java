package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableMongoAuditing
public class AiDemoApplication {

    public static void main(String[] args) {
        /**
         * 隐藏banner启动方式
         */
//        SpringApplication springApplication = new SpringApplication(AiDemoApplication.class);
//        //设置banner的模式为隐藏
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        //启动springboot应用程序
//        springApplication.run(args);
        SpringApplication.run(AiDemoApplication.class, args);
    }

}
