package com.example.demo.nlp_jieba;

import com.google.common.collect.Sets;
import com.huaban.analysis.jieba.WordDictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Set;

public class StopWordDictionary {

    private static StopWordDictionary singleton;
    private static final String STOP_DICT = "/stop-words.txt";
    public final Set<String> stopWords = Sets.newHashSet();

    public static StopWordDictionary getInstance() {
        if (singleton == null) {
            synchronized (WordDictionary.class) {
                if (singleton == null) {
                    singleton = new StopWordDictionary();
                    return singleton;
                }
            }
        }
        return singleton;
    }

    private StopWordDictionary() {
        this.loadDict();
    }

    private void loadDict() {
//        Thread.currentThread().getContextClassLoader().getResourceAsStream(STOP_DICT);
        InputStream is = this.getClass().getResourceAsStream(STOP_DICT);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            long s = System.currentTimeMillis();
            while (br.ready()) {
                String line = br.readLine();
                stopWords.add(line);
            }
            System.out.println(String.format(Locale.getDefault(), "stop dict load finished, time elapsed %d ms",
                    System.currentTimeMillis() - s));
        } catch (IOException e) {
            System.err.println(String.format(Locale.getDefault(), "%s load failure!", STOP_DICT));
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                System.err.println(String.format(Locale.getDefault(), "%s close failure!", STOP_DICT));
            }
        }
    }

    public Set<String> getStopWords() {
        return stopWords;
    }

}
