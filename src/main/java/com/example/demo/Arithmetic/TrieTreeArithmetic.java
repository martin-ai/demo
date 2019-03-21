package com.example.demo.Arithmetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

public class TrieTreeArithmetic {

    private TrieTreeBuilder trieTreeBuilder;

    private static final String TRIE_TREE_DICT = "/trie-tree.txt";
    private static volatile TrieTreeArithmetic singleton;

    private TrieTreeArithmetic() {
    }

    public static TrieTreeArithmetic getInstance() {
        if (singleton == null) {
            synchronized (TrieTreeArithmetic.class) {
                if (singleton == null) {
                    singleton = new TrieTreeArithmetic();
                    singleton.buildTree();
                }
            }
        }
        return singleton;
    }

    private void buildTree() {
        trieTreeBuilder = TrieTreeBuilder.newBuilder();
        InputStream is = this.getClass().getResourceAsStream(TRIE_TREE_DICT);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            while (br.ready()) {
                String line = br.readLine();
                trieTreeBuilder.addString(line);
            }
        } catch (IOException e) {
            System.err.println(String.format(Locale.getDefault(), "%s load failure!", TRIE_TREE_DICT));
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                System.err.println(String.format(Locale.getDefault(), "%s close failure!", TRIE_TREE_DICT));
            }
        }
    }

    public List<String> getKeyWordsByPrefix(String str) {
        return trieTreeBuilder.getKeyWordsByPrefix(str);
    }

}
