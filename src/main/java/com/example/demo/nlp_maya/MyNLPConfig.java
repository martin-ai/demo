package com.example.demo.nlp_maya;

import com.mayabot.nlp.segment.MynlpAnalyzer;
import com.mayabot.nlp.segment.Tokenizers;
import com.mayabot.nlp.segment.analyzer.DefaultMynlpAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyNLPConfig {

    @Bean
    public MynlpAnalyzer mynlpAnalyzer() {
        return new DefaultMynlpAnalyzer(Tokenizers.coreTokenizer());
    }

}
