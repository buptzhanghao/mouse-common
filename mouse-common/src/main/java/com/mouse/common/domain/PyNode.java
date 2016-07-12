package com.mouse.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghao
 * @version 1.0
 * @created 15/12/4
 */

public class PyNode {

    private String name;      // 结点的字符名称
    private int fre;          // 单词的词频
    private boolean end;      // 是否是单词结尾
    private boolean root;     // 是否是根结点
    private Map<String, PyNode> children; // 子节点信息

    public PyNode(String name) {
        this.name = name;
        if (children == null) {
            children = new HashMap<String, PyNode>();
        }
        setFre(0);
        setRoot(false);
        setEnd(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFre() {
        return fre;
    }

    public void setFre(int fre) {
        this.fre = fre;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public Map<String, PyNode> getChildren() {
        return children;
    }

    public void setChildren(Map<String, PyNode> children) {
        this.children = children;
    }

}
