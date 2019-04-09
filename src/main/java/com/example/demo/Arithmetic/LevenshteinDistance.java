package com.example.demo.Arithmetic;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串的编辑距离，又称为Levenshtein距离，由俄罗斯的数学家Vladimir Levenshtein在1965年提出。
 * 是指利用字符操作，把字符串A转换成字符串B所需要的最少操作数。
 * 其中，字符操作包括：
 * a) Insert a character 删除一个字符
 * b) Delete a character 插入一个字符
 * c) Replace a character 修改一个字符
 * <p>
 * 例如对于字符串"if"和"iff"，可以通过插入一个'f'或者删除一个'f'来达到目的。
 * 一般来说，两个字符串的编辑距离越小，则它们越相似。如果两个字符串相等，则它们的编辑距离为0（不需要任何操作）。
 * 不难分析出，两个字符串的编辑距离肯定不超过它们的最大长度（可以通过先把短串的每一位都修改成长串对应位置的字符，然后插入长串中的剩下字符）。
 */
public class LevenshteinDistance {

    private static final int n = 2;

    public static float calcLevenshteinDistance(String source, String target) {
        int sl = source.length();
        int tl = target.length();
        if (sl != 0 && tl != 0) {
            int cost = 0;
            if (sl >= n && tl >= n) {
                char[] sa = new char[sl + n - 1];

                int i;
                for (i = 0; i < sa.length; ++i) {
                    if (i < n - 1) {
                        sa[i] = 0;
                    } else {
                        sa[i] = source.charAt(i - n + 1);
                    }
                }

                print(sa, "sa");

                float[] p = new float[sl + 1];
                float[] d = new float[sl + 1];
                char[] t_j = new char[n];

                for (i = 0; i <= sl; ++i) {
                    p[i] = (float) i;
                }

                print(p, "p");

                for (int j = 1; j <= tl; ++j) {
                    int tn;
                    if (j >= n) {
                        t_j = target.substring(j - n, j).toCharArray();

                        print(t_j, "t_j");

                    } else {
                        for (tn = 0; tn < n - j; ++tn) {
                            t_j[tn] = 0;
                        }

                        for (tn = n - j; tn < n; ++tn) {
                            t_j[tn] = target.charAt(tn - (n - j));
                        }

                        print(t_j, "t_j");
                    }

                    d[0] = (float) j;

                    for (i = 1; i <= sl; ++i) {
                        cost = 0;
                        tn = n;

                        for (int ni = 0; ni < n; ++ni) {
                            if (sa[i - 1 + ni] != t_j[ni]) {
                                System.out.print("sa vs t_j > ");
                                System.out.print(sa[i - 1 + ni] + ":");
                                System.out.println(t_j[ni]);
                                ++cost;
                            } else if (sa[i - 1 + ni] == 0) {
                                --tn;
                            }
                        }

                        float ec = (float) cost / (float) tn;
                        System.out.println("ec:" + ec);
                        d[i] = Math.min(Math.min(d[i - 1] + 1.0F, p[i] + 1.0F), p[i - 1] + ec);


                    }
                    print(d, "d");

                    float[] _d = p;
                    p = d;
                    d = _d;

                }

                return 1.0F - p[sl] / (float) Math.max(tl, sl);
            } else {
                int i = 0;

                for (int ni = Math.min(sl, tl); i < ni; ++i) {
                    if (source.charAt(i) == target.charAt(i)) {
                        ++cost;
                    }
                }

                return (float) cost / (float) Math.max(sl, tl);
            }
        } else {
            return sl == tl ? 1.0F : 0.0F;
        }
    }

    private static void print(char[] s, String name) {
        System.out.println(String.format("%s:[%s]", name, StringUtils.join(s, ',')));
    }

    private static void print(float[] s, String name) {
        System.out.println(String.format("%s:[%s]", name, StringUtils.join(s, ',')));
    }

}
