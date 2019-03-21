package com.example.demo.baidunlp;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.nlp.ESimnetType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class AiSimpleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AipNlp aipNlpClient;
    @Autowired
    private DnnlmCnRepository dnnlmCnRepository;
    @Autowired
    private KeywordRepository keywordRepository;

    //词法分析接口向用户提供分词、词性标注、专名识别三大功能；能够识别出文本串中的基本词汇（分词），对这些词汇进行重组、标注组合后词汇的词性，并进一步识别出命名实体。
    public void lexer(String text/*最大512字节*/) {
        JSONObject res = aipNlpClient.lexer(text, null);
        System.out.println(res.toString(2));
    }

    //中文DNN语言模型接口用于输出切词结果并给出每个词在句子中的概率值,判断一句话是否符合语言表达习惯。
    public void dnnlmCn(String text/*最大512字节*/) {
        JSONObject res = aipNlpClient.dnnlmCn(text, null);
        System.out.println(res.toString(2));
//        dnnlmCnRepository.save(JSON.parseObject(res.toString(), DnnlmCn.class));
    }

    //输入两个词，得到两个词的相似度结果。
    public BigDecimal wordSimEmbedding(String word1, String word2/*最大64字节*/) {
        JSONObject res = aipNlpClient.wordSimEmbedding(word1, word2, null);
        System.out.println(res.toString(2));
        return res.getBigDecimal("score");
    }

    //短文本相似度接口用来判断两个文本的相似度得分。
    public BigDecimal simnet(String text1, String text2/*最大64字节*/) {
        // 传入可选参数调用接口
        // model 默认为"BOW"，可选"BOW"、"CNN"与"GRNN"
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "CNN");

        JSONObject res = aipNlpClient.simnet(text1, text2, null);
        System.out.println(res.toString(2));
        return res.getBigDecimal("score");
    }

    //评论观点抽取接口用来提取一条评论句子的关注点和评论观点，并输出评论观点标签及评论观点极性。
    public void commentTag(String text/*最大512字节*/) {
        JSONObject res = aipNlpClient.commentTag(text, ESimnetType.CAR, null);
        if (checkResponseParam(res)) {
            keywordRepository.save(JSON.parseObject(res.toString(), Keyword.class));
        }
    }

    //文章标签服务能够针对网络各类媒体文章进行快速的内容理解，根据输入含有标题的文章，输出多个内容标签以及对应的置信度，用于个性化推荐、相似文章聚合、文本内容分析等场景。
    public void keyword(String title/*最大80字节*/, String content/*最大65535字节*/) {
        JSONObject res = aipNlpClient.keyword(title, content, null);
        checkResponseParam(res);
        System.out.println(res.toString(2));
    }

    private boolean checkResponseParam(JSONObject res) {
        if (res.has("error_code")) {
            logger.error(String.format("%s:%s", String.valueOf(res.get("error_code")), res.getString("error_msg")));
            return false;
        }
        return true;
    }

}
