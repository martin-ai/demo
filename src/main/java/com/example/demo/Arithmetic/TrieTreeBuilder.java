package com.example.demo.Arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Trie树又名字典树，字典是由一组词组成的集合，而字典树对这个集合进行了结构化的组织，将字典用另一种表达方式进行了表达。
 * 首先字典书对一些具有公共前缀的词进行了“压缩”，大大减小了它占用的空间。同时对于字典内词的前缀检索也十分迅速。
 * <p>
 * 字典树通过从根节点到子节点的路径来表达一个此，图中红色节点为一个词的最后一个节点，
 * 也就是说上面的树代表的单词有abc、ab、bd、dda,也就是红色节点的个数。
 * 其中，根节点不表示任何字符。字典树压缩了存储结构，同时对于模糊匹配提供了很好的支持。
 * <p>
 * 适用场景：
 * 1.对于关键字的匹配，判断一个字符串内是否包含字典中的词，循环匹配显然是最傻的选择，
 * 那么这时候可以用Trie树，将输入字符串进行字典树匹配，命中则说明包含，不命中则说明不包含。
 * 2.做分词，将输入字符串，进行字典树匹配，进行分词，粒度需要自己掌控。
 * 3.前缀匹配，输入一个词的前缀，补全所有以此前缀开头的单词。
 * 基于路径匹配，可以使匹配过程中过滤掉大部分不符合的词，效率很快。
 * 但是这种只能做最简单的Case，如果引入权重，需要对命中单词做排序，难度就又高了一层。
 * <p>
 * 总结：
 * 1.对于字典树节点的结构就是Character + HashMap。来嵌套下一层的节点结合。
 * 2.而添加节点就是逐层的添加Character 直到没有Character 可填。
 * 3.而搜索就是定位最后一个字符所在的节点，然后对这个节点做一个深度优先的遍历，遍历出全部的单词。
 */
public class TrieTreeBuilder {

    private TrieNode rootNode;

    public static TrieTreeBuilder newBuilder() {
        return new TrieTreeBuilder();
    }

    private TrieTreeBuilder() {
        this.rootNode = new TrieNode(null, null);
    }

    //将关键字添加到Trie树中
    public TrieTreeBuilder addString(String keyword) {
        TrieNode node = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            Character c = keyword.charAt(i);
            node = node.addCharacter(c);
        }
        node.setIfWord(true);
        return this;
    }

    //根据关键字获取Trie树的全部节点
    public List<String> getKeyWordsByPrefix(String findStr) {
        List<String> suggestList = new ArrayList<String>();
        TrieNode findNode = rootNode;
        for (int i = 0; i < findStr.length(); i++) {
            Character c = findStr.charAt(i);
            findNode = findNode.getCharacter(c);
            if (findNode == null) {
                break;
            }
        }
        if (findNode != null) {
            if (findNode.isIfWord()) {
                suggestList.add(findStr);
            }
            getAllSubNodes(findNode, findStr, suggestList);
        }
        return suggestList;
    }

    private void getAllSubNodes(TrieNode node, String prefix, List<String> suggestList) {
        Map<Character, TrieNode> map = node.getNodeMap();
        if (map != null) {
            Set<Character> keySet = map.keySet();
            List<TrieNode> nodeList = new ArrayList<TrieNode>();
            for (Character c : keySet) {
                nodeList.add(map.get(c));
            }
            for (TrieNode subNode : nodeList) {
                Character m = subNode.getCharacter();
                String word = prefix + m;
                if (subNode.isIfWord()) {
                    suggestList.add(word);
                }
                getAllSubNodes(subNode, word, suggestList);
            }

        }
    }

}
