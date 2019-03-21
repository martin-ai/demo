package com.example.demo.Arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树节点
 */
public class TrieNode {
    //字符
    private Character character;
    //节点对应的子节点
    private Map<Character, TrieNode> nodeMap;
    //表示当前节点是否一个完整的词
    private boolean ifWord = false;

    public TrieNode(Character character, Map<Character, TrieNode> nodeMap) {
        this.character = character;
        this.nodeMap = nodeMap;
    }

    //添加字符
    public TrieNode addCharacter(Character c) {
        if (nodeMap == null) {
            this.nodeMap = new HashMap<Character, TrieNode>();
        }
        TrieNode node = nodeMap.get(c);
        if (node == null) {
            TrieNode node1 = new TrieNode(c, null);
            nodeMap.put(c, node1);
            node = node1;
        }
        return node;
    }

    public TrieNode getCharacter(Character c) {
        return nodeMap.get(c);
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Map<Character, TrieNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<Character, TrieNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public boolean isIfWord() {
        return ifWord;
    }

    public void setIfWord(boolean ifWord) {
        this.ifWord = ifWord;
    }
}
