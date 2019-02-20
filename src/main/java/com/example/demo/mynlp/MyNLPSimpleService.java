package com.example.demo.mynlp;

import com.alibaba.fastjson.JSONObject;
import com.mayabot.nlp.segment.MynlpAnalyzer;
import com.mayabot.nlp.segment.WordTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.io.StringReader;

@Service
public class MyNLPSimpleService {

    @Autowired
    private MynlpAnalyzer mynlpAnalyzer;

    public void parse(String text) {
        Reader reader = new StringReader(text);
        Iterable<WordTerm> result = mynlpAnalyzer.parse(reader);
        System.out.println(JSONObject.toJSONString(result));
    }

}