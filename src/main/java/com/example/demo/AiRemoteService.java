package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiRemoteService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AiConfig aiConfig;
    @Autowired
    private RestUtils restUtils;

    public String getToken() {
        StringBuffer url = new StringBuffer(aiConfig.getAiOauthUrl())
                .append("?grant_type=").append(aiConfig.getGrantType())
                .append("&client_id=").append(aiConfig.getClientId())
                .append("&client_secret=").append(aiConfig.getClientSecret());
        JSONObject jsonObject = restUtils.requestPost(url.toString());
        String accessToken = jsonObject.getString("access_token");
        Assert.assertNotNull("access_token为空", accessToken);
        logger.info("successfully baidu ai token : {}", accessToken);
        return accessToken;
    }

    public String getWordSimilarity() {
        StringBuffer url = new StringBuffer(aiConfig.getAiNlpUrl()).append("/word_emb_sim")
                .append("?access_token=").append(getToken());
        JSONObject jsonObject = restUtils.requestPost(url.toString());
        String accessToken = jsonObject.getString("access_token");
        Assert.assertNotNull("access_token为空", accessToken);
        logger.info("successfully baidu ai token : {}", accessToken);
        return accessToken;
    }

}
