package com.example.demo.Tools;

import com.example.demo.nlp_jieba.StopWordDictionary;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class MyStringUtils {

    private static final String REGEX_SYMBOL = "^[\\pP|\\pS|\\pZ|\r|\t|\n]*$";
    private static final String REGEX_DECIMALS = "^[.|0-9]*$";
    private static final String REGEX_ALPHA_NUMERIC = "^[0-9|a-z|A-Z]*$";
    private static final StopWordDictionary stopWordDictionary = StopWordDictionary.getInstance();

    public static String append(String oldStr, String newItem) {
        if (StringUtils.contains(oldStr, newItem)) return oldStr;

        String firstStr = StringUtils.defaultIfBlank(oldStr, null);
        String secondStr = StringUtils.defaultIfBlank(newItem, null);
        if (firstStr == null && secondStr == null) return null;

        return Joiner.on(",").skipNulls().join(firstStr, secondStr);
    }

    public static String merge(String olds, String news) {
        if (!ObjectUtils.allNotNull(olds, news)) {
            return ObjectUtils.firstNonNull(olds, news);
        }
        Set<String> mergeSet = Sets.newTreeSet();
        CollectionUtils.addAll(mergeSet, StringUtils.split(olds, ","));
        CollectionUtils.addAll(mergeSet, StringUtils.split(news, ","));
        return StringUtils.join(mergeSet, ",");
    }

    public static boolean isUsable(String str, boolean isFilterStopWords) {
        Thread.currentThread().getContextClassLoader().getResourceAsStream("xxx.txt");
        if (StringUtils.isEmpty(str)) {
            return false;
        } else if (str.matches(REGEX_SYMBOL)) {
            //过滤标点符号，制表符
            return false;
        } else if (str.matches(REGEX_DECIMALS)) {
            //过滤小数
            return false;
        } else if (str.matches(REGEX_ALPHA_NUMERIC) && !StringUtils.isAlpha(str)) {
            //过滤包含数字与字母的字符串 但不包含纯字母
            return false;
        } else if (isFilterStopWords && stopWordDictionary.getStopWords().contains(str)) {
            //过滤停用词
            return false;
        } else {
            //过滤数字
            return !StringUtils.isNumeric(str);
        }
    }

}
