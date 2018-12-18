package com.example.demo.Ai;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiSimpleService {

    @Autowired
    private AipNlp aipNlpClient;
    @Autowired
    private DnnlmCnRepository dnnlmCnRepository;

    public void test() {
        // 调用接口
        String text = "床前明月光";
        // 传入可选参数调用接口
        JSONObject res = aipNlpClient.dnnlmCn(text, null);
        System.out.println(res.toString(2));
        DnnlmCnBean dnnlmCnBean = JSON.parseObject(res.toString(), DnnlmCnBean.class);
        System.out.println(dnnlmCnBean);
        dnnlmCnRepository.save(dnnlmCnBean);
    }

}
