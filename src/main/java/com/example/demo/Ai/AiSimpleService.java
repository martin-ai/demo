package com.example.demo.Ai;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiSimpleService {

    @Autowired
    private AipNlp aipNlpClient;


    public void test() {
        // 调用接口
        String text = "百度是一家高科技公司";
        JSONObject res = aipNlpClient.lexer(text, null);
        System.out.println(res.toString(2));
    }

}
