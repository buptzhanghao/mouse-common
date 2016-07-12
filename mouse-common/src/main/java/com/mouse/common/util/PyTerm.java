package com.mouse.common.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/11
 */
public class PyTerm {

    private int index;

    private List<String> list;

    private double avgLength;

    public PyTerm(int index, List<String> list) {
        this.index = index;
        this.list = list;
    }

    public PyTerm() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void addIndex() {
        this.index = this.index + 1;
    }

    public void addPinyin(String pinyin) {
        if ( this.getList() == null ) {
            this.list = Lists.newArrayList();
        }
        this.getList().add(pinyin);
    }

    @Override
    public String toString() {
        return "PyTerm {"
                + "list = " + list
                + "}";
    }

    public static PyTerm copy(PyTerm pyTerm) {
        PyTerm copyTerm = new PyTerm();
        copyTerm.setIndex(pyTerm.getIndex());

        List<String> list = pyTerm.getList();
        if ( list == null ) {
            return copyTerm;
        }

        List<String> copyList = Lists.newArrayList();
        for ( String s : list ) {
            copyList.add(s);
        }
        copyTerm.setList(copyList);
        return copyTerm;
    }

    public double calculateAvgLen() {
        int sum = 0;
        for ( String s : this.list ) {
            sum += s.length();
        }
        this.avgLength = (double)sum / (double)list.size();
        return this.avgLength;
    }

    public double getAvgLen() {
        if ( this.avgLength == 0 ) {
            return calculateAvgLen();
        } else {
            return this.avgLength;
        }
    }
}
