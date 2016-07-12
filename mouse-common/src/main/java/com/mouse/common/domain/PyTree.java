package com.mouse.common.domain;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/11
 */
public class PyTree {

    PyNode root;

    public PyTree(String name) {
        root = new PyNode(name);
        root.setFre(0);
        root.setEnd(false);
        root.setRoot(true);
    }

    public void insert(String word) {

        PyNode node = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (node.getChildren().containsKey(String.valueOf(words[i]))) {
                if (i == words.length - 1) {
                    PyNode endNode = node.getChildren().get(String.valueOf(words[i]));
                    endNode.setFre(endNode.getFre() + 1);
                    endNode.setEnd(true);
                }
            } else {
                PyNode newNode = new PyNode(String.valueOf(words[i]));
                if (i == words.length - 1) {
                    newNode.setFre(1);
                    newNode.setEnd(true);
                    newNode.setRoot(false);
                }

                node.getChildren().put(String.valueOf(words[i]), newNode);
            }

            node = node.getChildren().get(String.valueOf(words[i]));
        }

    }

    public int searchFre(String word) {
        int fre = -1;

        PyNode node = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (node.getChildren().containsKey(String.valueOf(words[i]))) {
                node = node.getChildren().get(String.valueOf(words[i]));
                fre = node.getFre();
            } else {
                fre = -1;
                break;
            }
        }
        return fre;
    }

    public PyNode getRoot() {
        return root;
    }

    public void setRoot(PyNode root) {
        this.root = root;
    }

}
