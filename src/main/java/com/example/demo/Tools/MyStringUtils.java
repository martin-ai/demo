package com.example.demo.Tools;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class MyStringUtils {

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

}
