package com.example.demo.nlp_jieba;

import com.example.demo.Tools.MyStringUtils;
import com.google.common.collect.Lists;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiebaService {

    /**
     * @return List<String> 为去除停用词的分词结果，Integer为没有去除停用词的分词结果总数
     */
    public Pair<List<String>, Integer> segment(String sentence, boolean isFilterStopWords) {
        List<String> result = Lists.newArrayList();
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> segTokenList = segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH);
        for (SegToken segToken : segTokenList) {
            if (MyStringUtils.isUsable(segToken.word, isFilterStopWords)) {
                result.add(StringUtils.upperCase(segToken.word));
            }
        }
        return Pair.of(result, segTokenList.size());
    }

}
