package com.example.demo.Arithmetic;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;

/**
 * Soundex算法是一种拼音算法:用于按英语发音来索引姓名，它最初由美国人口调查局开发
 * Soundex方法返回一个表示姓名的四字符代码，由一个英文字母后跟三个数字构成。
 * 字母是姓名的首字母，数字对姓名中剩余的辅音字母编码,发音相近的姓名具有相同的Soundex代码。
 * 1、将英文字按以下规则替换（除第一个字符外)
 * 2、去除0，对于重复的字符只保留一个
 * 3、返回前4个字符，不足4位以0补足
 * <p>
 * 可用mysql验证结果 SELECT SOUNDEX('martin')
 */
public class SoundexArithmetic {

    /*实现26个英文字母的映射*/
    public static final char[] MAP = {
            //A   B    C    D    E    F    G    H    I    J    K    L    M
            '0', '1', '2', '3', '0', '1', '2', '0', '0', '2', '2', '4', '5',
            //N   O    P    Q    R    S    T    U    V    W    X    Y    Z
            '5', '0', '1', '2', '6', '2', '3', '0', '1', '0', '2', '0', '2'
    };

    private static final int MAX_LENGTH = 4;

    public static String calc(String oriStr) {
        if (StringUtils.isBlank(oriStr)) {
            return null;
        }
        LinkedHashSet<Character> result = new LinkedHashSet<>();
        char[] str = StringUtils.trim(oriStr).replaceAll("[^A-Za-z]", "").toCharArray();
        for (int i = 0; i < str.length && result.size() <= MAX_LENGTH; i++) {
            if (i == 0) {
                result.add(str[i]);
            } else {
                char c = MAP[Character.getNumericValue(str[i]) - Character.getNumericValue('A')];
                if (c != '0') {
                    result.add(c);
                }
            }
        }
        return StringUtils.rightPad(Joiner.on("").join(result), MAX_LENGTH, '0');
    }

}
