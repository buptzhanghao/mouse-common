package com.mouse.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mouse.common.domain.PyMap;
import com.mouse.common.domain.PyNode;
import com.mouse.common.domain.PyTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/11
 */
public class PySpliter {

    private static final Logger log = LoggerFactory.getLogger(PySpliter.class);

    private static final Pattern ALL_PY_PATTERN = Pattern.compile("[a-zA-Z]+");

    /** 拼音字典树      */
    private PyTree tree;

    /** 自定义的拼音组合 */
    private Map<String, List<String>> selfDefinePyMap;

    private PySpliter() {
        initData();
    }

    /**
     * =================================================================================================================
     *                                           ||      初始化拼音拆分器     ||
     *                                           ||                        ||
     *                                           ||      1.构造拼音字典树     ||
     *                                           ||      2.获取自定义拼音     ||
     * =================================================================================================================
     */
    private void initData() {
        tree = initPyTree();
        selfDefinePyMap = initSelfDefPinyin();
    }

    /**
     * 构造拼音字典树
     */
    private PyTree initPyTree() {
        PyTree tree = new PyTree("PinYin");
        try {
            for ( int i = 0 ; i != PyMap.LENGTH; i++ ) {
                tree.insert(PyMap.PINYIN[i]);
            }
        } catch ( Exception e ) {
            log.error("Init Trie Tree For Pinyin error.", e);
        }
        return tree;
    }

    /**
     * 从配置文件中读取自定义的pinyin组合
     * Eg: "xian" --> "xi","an"
     */
    private Map<String, List<String>> initSelfDefPinyin() {
        Map<String, List<String>> map = Maps.newHashMap();
        InputStream input = PinyinUtil.class.getResourceAsStream("/pinyin.properties");
        if (input == null) {
            log.warn("Pinyin config file does not exist: " + "/pinyin.properties");
            return map;
        }

        Properties py = new Properties();
        try {
            py.load(input);
        } catch ( IOException e) {
            log.error("Failed to load pinyin.properties");
            return map;
        }

        Enumeration enums = py.propertyNames();
        while ( enums.hasMoreElements() ) {
            String key = (String)enums.nextElement();
            String value = py.getProperty(key);

            String[] values = value.split(",");
            map.put(key, Arrays.asList(values));
        }
        return map;
    }

    /**
     * =================================================================================================================
     *                                    ||               拆分拼音                   ||
     *                                    ||                                        ||
     *                                    ||   不管输入的字符串是否是拼音                ||
     *                                    ||   总是尽可能的拆分                        ||
     *                                    ||   Eg:"elasticsearch"                   ||
     *                                    ||   r=[e, la, ti, se, a], l=[s, c, r, ch]||
     * =================================================================================================================
     */

    /**
     * 切分拼音, 默认最长匹配
     * “guo lao shi 14” --> r=[guo, lao, shi, 1, 4], l=[]
     * r中存放安全切分的拼音
     * l中存放无法切分的拼音
     */
    public Map<String, List<String>> splitToPinyin(String spell) {
        Map<String, List<String>> pyMap = Maps.newConcurrentMap();
        String s = filtPinyin(spell);
        if ( s == null || s.length() == 0 ) {
            return pyMap;
        }
        char[] letters = s.toCharArray();

        /** 完整的拼音列表 */
        List<String> list = Lists.newArrayList();
        /** 不完整的拼音列表 */
        List<String> charList = Lists.newArrayList();

        String spells = "";

        PyNode node = tree.getRoot();
        int i = 0;
        while ( i < letters.length ) {
            if ( letters[i] < 'a' || letters[i] > 'z' ) {
                if ( !("".equals(spells)) ) {
                    if ( node.isEnd() ) {
                        list.add(spells);
                    } else {
                        charList.add(spells);
                    }
                }
                spells = String.valueOf(letters[i]);
                node = tree.getRoot();
                list.add(spells);
                spells = "";
                i++;
            } else if (node.getChildren().containsKey(String.valueOf(letters[i]))) {
                spells += letters[i];
                node = node.getChildren().get(String.valueOf(letters[i]));
                i++;
            } else {
                if (node.isEnd()) {
                    list.add(spells);
                    node = tree.getRoot();
                    spells = "";
                } else {
                    if ( !"".equals(spells) ) {
                        charList.add(spells);
                        node = tree.getRoot();
                        spells = "";
                    } else {
                        charList.add(String.valueOf(letters[i]));
                        spells = "";
                        i++;
                    }
                }
            }
        }

        if ( !("".equals(spells)) ) {
            if ( node.isEnd() ) {
                list.add(spells);
            } else {
                charList.add(spells);
            }
        }

        pyMap.put("r", list);
        pyMap.put("l", charList);
        return pyMap;
    }

    /**
     * 拆分拼音,num参数控制切分的拼音长度,使切分的拼音尽可能的小于num
     * Eg:
     * num = 4 时 "zhanghaouuuu" --> r=[zhan, hao], l=[g, u, u, u, u]
     * num = 5 时 "zhanghaouuuu" --> r=[zhang, hao], l=[u, u, u, u]
     */
    public Map<String, List<String>> splitToPinyin(String spell, int num) {
        Map<String, List<String>> pyMap = Maps.newConcurrentMap();
        String s = filtPinyin(spell);
        if ( s == null || s.length() == 0 ) {
            return pyMap;
        }
        char[] letters = s.toCharArray();

        /** 完整的拼音列表 */
        List<String> list = Lists.newArrayList();
        /** 不完整的拼音列表 */
        List<String> charList = Lists.newArrayList();

        String spells = "";
        PyNode node = tree.getRoot();

        int i = 0 ;
        while ( i < letters.length ) {
            if (letters[i] < 'a' || letters[i] > 'z') {
                if (!("".equals(spells))) {
                    if (node.isEnd()) {
                        list.add(spells);
                    } else {
                        charList.add(spells);
                    }
                }
                spells = "" + letters[i];
                node = tree.getRoot();
                list.add(spells);
                spells = "";
                i++;
            } else if (node.getChildren().containsKey(String.valueOf(letters[i]))) {
                spells += letters[i];
                node = node.getChildren().get(String.valueOf(letters[i]));
                if ( node.isEnd() && spells.length() >= num ) {
                    list.add(spells);
                    node = tree.getRoot();
                    spells = "";
                }
                i++;
            } else {
                if (node.isEnd()) {
                    list.add(spells);
                    node = tree.getRoot();
                    spells = "";
                } else {
                    if ( !"".equals(spells) ) {
                        charList.add(spells);
                        node = tree.getRoot();
                        spells = "";
                    } else {
                        charList.add(String.valueOf(letters[i]));
                        spells = "";
                        i++;
                    }
                }
            }
        }

        if (!("".equals(spells))) {
            if ( node.isEnd() ) {
                list.add(spells);
            } else {
                charList.add(spells);
            }
        }

        pyMap.put("r", list);
        pyMap.put("l", charList);
        return pyMap;
    }

    /**
     * 切分拼音 最长匹配和最短匹配可选
     * los = true 最长匹配； los = false 最短匹配
     */
    public Map<String, List<String>> splitToPinyin(String spell, boolean los) {
        if ( los ) {
            return splitToPinyin(spell);
        }

        Map<String, List<String>> pyMap = Maps.newConcurrentMap();
        String s = filtPinyin(spell);
        if ( s == null || s.length() == 0 ) {
            return pyMap;
        }
        char[] letters = s.toCharArray();

        /** 完整的拼音列表 */
        List<String> list = Lists.newArrayList();
        /** 不完整的拼音列表 */
        List<String> charList = Lists.newArrayList();

        String spells = "";
        PyNode node = tree.getRoot();

        int i = 0 ;
        while ( i < letters.length ) {
            if (letters[i] < 'a' || letters[i] > 'z') {
                if (!("".equals(spells))) {
                    if (node.isEnd()) {
                        list.add(spells);
                    } else {
                        charList.add(spells);
                    }
                }
                spells = "" + letters[i];
                node = tree.getRoot();
                list.add(spells);
                spells = "";
                i++;
            } else if (node.getChildren().containsKey(String.valueOf(letters[i]))) {
                spells += letters[i];
                node = node.getChildren().get(String.valueOf(letters[i]));
                if ( node.isEnd()) {
                    list.add(spells);
                    node = tree.getRoot();
                    spells = "";
                }
                i++;
            } else {
                if (node.isEnd()) {
                    list.add(spells);
                    node = tree.getRoot();
                    spells = "";
                } else {
                    if ( !"".equals(spells) ) {
                        charList.add(spells);
                        node = tree.getRoot();
                        spells = "";
                    } else {
                        charList.add(String.valueOf(letters[i]));
                        spells = "";
                        i++;
                    }
                }
            }
        }

        if (!("".equals(spells))) {
            if ( node.isEnd() ) {
                list.add(spells);
            } else {
                charList.add(spells);
            }
        }

        pyMap.put("r", list);
        pyMap.put("l", charList);
        return pyMap;
    }

    /**
     * 根据过滤器切分拼音
     * 过滤器由初始化时设定
     */
    public Map<String, List<String>> splitToPinyinBySelfFilter(String spell) {
        Map<String, List<String>>  map = splitToPinyin(spell);

        if ( map == null || map.size() == 0 ) {
            return Maps.newHashMap();
        }

        List<String> list = map.get("r");
        List<String> resultList = Lists.newArrayList();

        for ( String s : list ) {
            resultList.add(s);
            for ( String x : selfDefinePyMap.keySet() ) {
                if ( s.equals(x) ) {
                    resultList.remove(s);
                    resultList.addAll(selfDefinePyMap.get(x));
                }
            }
        }

        map.put("r", resultList);
        return map;
    }



    /**
     * =================================================================================================================
     *                                     ||        获取拼音组合             ||
     *                                     ||                               ||
     *                                     ||   输入字符串一定是完整的拼音组合    ||
     *                                     ||   如果是非拼音组合则返回空列表      ||
     *                                     ||   如果是完整组合，则返回拼音组合    ||
     * =================================================================================================================
     */

    /**
     * 从字符串中获取匹配程度最高的拼音组合
     * 由于算法的原因,这个匹配程度只是近似的,不过大多数情况下都适用
     */
    public List<String> getMostMatchPyList( String spell ) {
        List<String> list = Lists.newArrayList();
        List<PyTerm> pyTerms = getPySets(spell);
        pyTerms = sortPyTerms(pyTerms);
        if ( pyTerms == null || pyTerms.size() == 0 ){
            return list;
        }
        return pyTerms.get(0).getList();
    }



    /**
     * 从字符串中获取匹配程度最高的拼音组合
     * 和getMostMatchPyList方法的却别在于:该方法首先会过滤掉非[a-zA-Z]区间的字符
     * 然后拆分为多个拼音字符串进行提取
     * Eg: "zhanghao15lixu04"  --> [zhang, hao, li, xu]
     */
    public List<String> getMostMatchPyListWithFilt( String spell ) {
        List<String> list = Lists.newArrayList();
        List<PyTerm> pyTerms = getPySetsWithFilt(spell);
        pyTerms = sortPyTerms(pyTerms);
        if ( pyTerms == null || pyTerms.size() == 0 ){
            return list;
        }
        return pyTerms.get(0).getList();
    }

    /**
     * 获取一个字符串中所有可能的拼音组合
     */
    public List<PyTerm> getPySets( String spell ) {
        List<PyTerm> result = Lists.newArrayList();
        List<PyTerm> list = Lists.newArrayList();
        char[] letters = spell.toCharArray();
        if ( letters.length == 0 || !isAllEN(spell)) {
            return list;
        }

        PyNode node = tree.getRoot();
        int i = 0;
        char ch = letters[i];

        while (node.getChildren().containsKey(String.valueOf(ch))) {
            i++;
            node = node.getChildren().get(String.valueOf(ch));
            if (node.isEnd() && i != letters.length && isFirstPyChar(letters[i])) {
                PyTerm term = new PyTerm();
                term.setIndex(i);
                term.addPinyin(spell.substring(0, i));
                list.add(term);
            } else if (node.isEnd() && i == letters.length) {
                PyTerm term = new PyTerm();
                term.setIndex(i);
                term.addPinyin(spell.substring(0, i));
                list.add(term);
            }
            if ( i != spell.length() ) {
                ch = letters[i];
            }
        }

        int size = list.size();
        boolean flag = (size != 0);
        while ( flag && size > 0 ) {
            node = tree.getRoot();
            PyTerm term = list.get(size - 1);
            if ( term.getIndex() == spell.length() ) {
                result.add(term);
                list.remove(size -1);
            } else {
                list.remove(size -1);
                int index = term.getIndex();
                while ( index != spell.length() && node.getChildren().containsKey(String.valueOf(letters[index]))) {
                    index++;
                    node = node.getChildren().get(String.valueOf(letters[index - 1]));
                    if (node.isEnd() && index != letters.length && isFirstPyChar(letters[index])) {
                        PyTerm term1 = PyTerm.copy(term);
                        term1.setIndex(index);
                        term1.addPinyin(spell.substring(term.getIndex(), index));
                        list.add(term1);
                    } else if (node.isEnd() && index == letters.length) {
                        PyTerm term1 = PyTerm.copy(term);
                        term1.setIndex(index);
                        term1.addPinyin(spell.substring(term.getIndex(), index));
                        list.add(term1);
                    }
                }
            }

            size = list.size();
            flag = false;
            for ( PyTerm term2 : list ) {
                if ( term2.getIndex() != spell.length() ) {
                    flag = true;
                    break;
                }
            }
        }

        if ( !flag && size != 0 ) {
            result.addAll(list);
        }

        return result;
    }

    /**
     * 从字符串中提取可能的拼音组合
     */
    public List<PyTerm> getPySetsWithFilt(String spell) {
        List<PyTerm> list = Lists.newArrayList();
        String s = spell.replaceAll("[^a-zA-Z]", " ").trim();
        String[] arr = s.split("\\s+");
        List<List<PyTerm>> termList = Lists.newArrayList();

        for ( String str : arr ) {
            List<PyTerm> pyTerms = getPySets(str);
            if ( pyTerms == null || pyTerms.size() == 0 ) {
                return list;
            }
            termList.add(pyTerms);
        }

        if ( termList.size() == 0 ) {
            return list;
        }

        int i = 0;
        List<PyTerm> pyTerms1 = termList.get(i);
        while( i != termList.size() - 1) {
            List<PyTerm> pyTerms2 = termList.get(i + 1);
            i++;

            List<PyTerm> list1 = Lists.newArrayList();
            for ( PyTerm pyTerm1 : pyTerms1 ) {
                for ( PyTerm pyTerm2 : pyTerms2 ) {
                    PyTerm pyTerm = PyTerm.copy(pyTerm1);
                    pyTerm.getList().addAll(pyTerm2.getList());
                    list1.add(pyTerm);
                }
            }
            pyTerms1 = list1;
        }

        return pyTerms1;
    }

    /**
     * =================================================================================================================
     *                                   ||             抽取拼音组合              ||
     *                                   ||                                     ||
     *                                   ||     考虑到输入拼音字符串的时候有缺失      ||
     *                                   ||     尾部有可能不是完整拼音        ||
     * =================================================================================================================
     */

    /**
     * 从字符串中提取匹配程度最高的拼音组合
     * autoComplete = true 表示自动补全    Eg: "zhanghaoho" --> ["zhang","hao","hou"],["zhang","hao","hong"]
     * autoComplete = false 表示不自动补全 Eg: "zhanghaoho" --> ["zhang","hao","ho"]
     */
    public Map<String, List<String>> extractMostMatchPyList( String spell, boolean autoComplete ) {
        return autoComplete ? extractMostMatchPyList1(spell) : extractMostMatchPyList0(spell);
    }

    public Map<String, List<String>> extractMostMatchPyListWithFilt( String spell, boolean autoComplete ) {
        return autoComplete ? extractMostMatchPyListWithFilt1(spell) : extractMostMatchPyListWithFilt0(spell);
    }


    private Map<String, List<String>> extractMostMatchPyList0( String spell ) {
        Map<String, List<String>> map = Maps.newHashMap();
        map.put("py", new ArrayList<String>());
        map.put("ac", new ArrayList<String>());
        List<PyTerm> pyTerms = extractPySet(spell);
        pyTerms = sortPyTerms(pyTerms);
        if ( pyTerms == null || pyTerms.size() == 0 ){
            return map;
        }
        map.put("py", pyTerms.get(0).getList());
        return map;
    }

    /**
     * 从字符串中提取匹配程度最高的拼音组合
     * 自动补全
     */
    private Map<String, List<String>> extractMostMatchPyList1( String spell ) {
        Map<String, List<String>> map = Maps.newHashMap();
        map.put("py", new ArrayList<String>());
        map.put("ac", new ArrayList<String>());
        List<PyTerm> pyTerms = extractPySet(spell);
        pyTerms = sortPyTerms(pyTerms);
        if ( pyTerms == null || pyTerms.size() == 0 ){
            return map;
        }
        List<String> list = pyTerms.get(0).getList();
        if ( list == null || list.size() == 0 ) {
            return map;
        }
        if ( isPyCell(list.get(list.size() - 1)) ) {
            map.put("py", list);
            return map;
        } else {
            map.put("py", list);
            map.put("ac", getPyStartWith(list.get(list.size() - 1)));
            return map;
        }
    }

    private Map<String, List<String>> extractMostMatchPyListWithFilt0( String spell ) {
        Map<String, List<String>> map = Maps.newHashMap();
        map.put("py", new ArrayList<String>());
        map.put("ac", new ArrayList<String>());
        List<PyTerm> pyTerms = extractPySetsWithFilt(spell);
        pyTerms = sortPyTerms(pyTerms);
        if ( pyTerms == null || pyTerms.size() == 0 ){
            return map;
        }
        map.put("py", pyTerms.get(0).getList());
        return map;
    }

    private Map<String, List<String>> extractMostMatchPyListWithFilt1( String spell ) {
        Map<String, List<String>> map = Maps.newHashMap();
        map.put("py", new ArrayList<String>());
        map.put("ac", new ArrayList<String>());
        List<PyTerm> pyTerms = extractPySetsWithFilt(spell);
        pyTerms = sortPyTerms(pyTerms);
        if ( pyTerms == null || pyTerms.size() == 0 ){
            return map;
        }
        List<String> list = pyTerms.get(0).getList();
        if ( list == null || list.size() == 0 ) {
            return map;
        }
        if ( isPyCell(list.get(list.size() - 1)) ) {
            map.put("py", list);
            return map;
        } else {
            map.put("py", list);
            map.put("ac", getPyStartWith(list.get(list.size() - 1)));
            return map;
        }
    }

    /**
     * 从字符串中提取可能的拼音组合
     */
    public List<PyTerm> extractPySetsWithFilt(String spell) {
        List<PyTerm> list = Lists.newArrayList();
        String s = spell.replaceAll("[^a-zA-Z]", " ").trim();
        String[] arr = s.split("\\s+");
        List<List<PyTerm>> termList = Lists.newArrayList();

        for ( int i = 0 ; i != arr.length; i++ ) {
            String str = arr[i];
            List<PyTerm> pyTerms;
            if ( i != arr.length - 1 ) {
                pyTerms = getPySets(str);
            } else {
                pyTerms = extractPySet(str);
            }
            if ( pyTerms == null || pyTerms.size() == 0 ) {
                return list;
            }
            termList.add(pyTerms);
        }

        if ( termList.size() == 0 ) {
            return list;
        }

        int i = 0;
        List<PyTerm> pyTerms1 = termList.get(i);
        while( i != termList.size() - 1) {
            List<PyTerm> pyTerms2 = termList.get(i + 1);
            i++;

            List<PyTerm> list1 = Lists.newArrayList();
            for ( PyTerm pyTerm1 : pyTerms1 ) {
                for ( PyTerm pyTerm2 : pyTerms2 ) {
                    PyTerm pyTerm = PyTerm.copy(pyTerm1);
                    pyTerm.getList().addAll(pyTerm2.getList());
                    list1.add(pyTerm);
                }
            }
            pyTerms1 = list1;
        }

        return pyTerms1;
    }


    /**
     * 从拼音字符串中提取拼音
     * 保留尾部不是拼音的组合
     * Eg: "zhanghaoho" --> [PyTerm {list = [zhang, hao, ho]}, PyTerm {list = [zhang, ha, o, ho]}]
     */
    public List<PyTerm> extractPySet(String spell) {

        List<PyTerm> result = Lists.newArrayList();
        List<PyTerm> result1 = Lists.newArrayList();
        List<PyTerm> list = Lists.newArrayList();
        char[] letters = spell.toCharArray();
        if ( letters.length == 0 || !isAllEN(spell)) {
            return list;
        }

        PyNode node = tree.getRoot();
        int i = 0;
        char ch = letters[i];

        while (node.getChildren().containsKey(String.valueOf(ch))) {
            i++;
            node = node.getChildren().get(String.valueOf(ch));
            if (node.isEnd() && i != letters.length && isFirstPyChar(letters[i])) {
                PyTerm term = new PyTerm();
                term.setIndex(i);
                term.addPinyin(spell.substring(0, i));
                list.add(term);
            } else if (node.isEnd() && i == letters.length) {
                PyTerm term = new PyTerm();
                term.setIndex(i);
                term.addPinyin(spell.substring(0, i));
                list.add(term);
            }
            if ( i != spell.length() ) {
                ch = letters[i];
            }
        }

        int size = list.size();
        if ( size == 0 ) {
            PyTerm term = new PyTerm();
            term.setIndex(spell.length());
            term.addPinyin(spell);
            list.add(term);
            return list;
        }

        boolean flag = (size != 0);
        while ( flag && size > 0 ) {
            node = tree.getRoot();
            PyTerm term = list.get(size - 1);
            if ( term.getIndex() == spell.length() ) {
                result.add(term);
                list.remove(size -1);
            } else {
                list.remove(size -1);
                int index = term.getIndex();
                while ( index != spell.length() && node.getChildren().containsKey(String.valueOf(letters[index]))) {
                    index++;
                    node = node.getChildren().get(String.valueOf(letters[index - 1]));
                    if (node.isEnd() && index != letters.length && isFirstPyChar(letters[index])) {
                        PyTerm term1 = PyTerm.copy(term);
                        term1.setIndex(index);
                        term1.addPinyin(spell.substring(term.getIndex(), index));
                        list.add(term1);
                    } else if (node.isEnd() && index == letters.length) {
                        PyTerm term1 = PyTerm.copy(term);
                        term1.setIndex(index);
                        term1.addPinyin(spell.substring(term.getIndex(), index));
                        list.add(term1);
                    } else if ( index == letters.length ) {
                        PyTerm term1 = PyTerm.copy(term);
                        term1.setIndex(index);
                        term1.addPinyin(spell.substring(term.getIndex(), index));
                        result1.add(term1);
                    }
                }
            }

            size = list.size();
            flag = false;
            for ( PyTerm term2 : list ) {
                if ( term2.getIndex() != spell.length() ) {
                    flag = true;
                    break;
                }
            }
        }

        if ( !flag && size != 0 ) {
            result.addAll(list);
        }

        if ( result.size() == 0 ) {
            return result1;
        } else {
            return result;
        }
    }



    /**
     * =================================================================================================================
     *                                            ||      辅助方法     ||
     * =================================================================================================================
     */

    /**
     * 过滤拼音字符串
     */
    private String filtPinyin( String spell ) {
        if ( spell == null || spell.length() == 0 ) {
            return spell;
        }
        String x = spell.toLowerCase().replaceAll("、", "").replaceAll("<","")
                .replaceAll(">", "").replaceAll("\\{", "").replaceAll("\\}", "")
                .replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\(", "")
                .replaceAll("\\)", "").replaceAll("\\.", "").replaceAll("\\*", "")
                .replace(" ", "");
        return x.trim();
    }

    /**
     * 对拼音组合的匹配程度进行排序
     * 排最前面的匹配程度最高 （ 由于算法的原因,这个匹配程度只是近似的,不过大多数情况下都适用 ）
     */
    private List<PyTerm> sortPyTerms(List<PyTerm> pyTerms) {
        List<PyTerm> list = Lists.newArrayList();
        if ( pyTerms == null || pyTerms.size() == 0 ) {
            return list;
        } else {
            for (PyTerm term : pyTerms) {
                term.calculateAvgLen();
            }

            Collections.sort(pyTerms, new Comparator<PyTerm>() {
                @Override
                public int compare(PyTerm o1, PyTerm o2) {
                    if (o1.getAvgLen() > o2.getAvgLen()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            return pyTerms;
        }
    }

    /**
     * 判断一个字符串是否全是字符
     */
    public boolean isAllEN( String spell ) {
        if ( spell == null || spell.length() == 0 ) {
            return false;
        }
        Matcher matcher = ALL_PY_PATTERN.matcher(spell);
        return matcher.matches();
    }

    /**
     * 判断一个字符是否可能是组成拼音的第一个字符
     */
    private boolean isFirstPyChar( char ch ) {
        PyNode node = tree.getRoot();
        return node.getChildren().containsKey(String.valueOf(ch));
    }

    /**
     * 判断一个字符串是否是一个拼音最小单元
     */
    public boolean isPyCell( String spell ) {
        PyNode node = tree.getRoot();
        char[] letters = spell.toCharArray();
        if ( letters.length == 0 || !isAllEN(spell)) {
            return false;
        }
        for ( char ch : letters ) {
            if (node.getChildren().containsKey(String.valueOf(ch))) {
                node = node.getChildren().get(String.valueOf(ch));
            } else {
                return false;
            }
        }
        return node.isEnd();
    }

    /**
     * 获取所有以spell开头的拼音
     */
    public List<String> getPyStartWith( String spell ) {
        List<String> list = Lists.newArrayList();
        for ( String py : PyMap.PINYIN ) {
            if ( py.startsWith(spell) ) {
                list.add(py);
            }
        }
        return list;
    }


    public List<String> getMostPossiblePyTerm( String spell ) {
        return null;
    }

    public List<PyTerm> getAllPyTerms( String spell ) {
        List<PyTerm> result = Lists.newArrayList();
        Pattern pattern = Pattern.compile("[a-zA-Z]*([^a-zA-Z]+)[a-zA-Z]*");
        Matcher matcher = pattern.matcher(spell);

        int i = 0;
        List<String> list = Lists.newArrayList();

        while (matcher.find()){
            String str = matcher.group(1);
            int index = spell.indexOf(str);
            if ( index != 0 ) {
                String s = spell.substring(i, index);
                list.add(s);
            }
            i = index + str.length();
            list.add(str);
        }

        boolean endByEN = false;
        if ( i != spell.length() ) {
            String s = spell.substring(i, spell.length());
            list.add(s);
            endByEN = true;
        }

        List<List<PyTerm>> termList = Lists.newArrayList();
        for ( int x = 0 ; x != list.size() ; x++) {
            String s = list.get(x);
            if ( x != list.size() -1 ) {
                if (isAllEN(s)) {
                    List<PyTerm> pyTerms = getPySets(s);
                    if ( pyTerms == null || pyTerms.size() == 0 ) {
                        return result;
                    } else {
                        termList.add(pyTerms);
                    }
                } else {
                    List<PyTerm> pyTerms = Lists.newArrayList();
                    PyTerm term = new PyTerm();
                    term.addPinyin(s);
                    pyTerms.add(term);
                    termList.add(pyTerms);
                }
            } else {
                if ( endByEN ) {
                    List<PyTerm> pyTerms = extractPySet(s);
                    if ( pyTerms == null || pyTerms.size() == 0 ) {
                        return result;
                    } else {
                        termList.add(pyTerms);
                    }
                } else {
                    List<PyTerm> pyTerms = Lists.newArrayList();
                    PyTerm term = new PyTerm();
                    term.addPinyin(s);
                    pyTerms.add(term);
                    termList.add(pyTerms);
                }
            }
        }

        int x = 0;
        List<PyTerm> pyTerms1 = termList.get(x);
        while( x != termList.size() - 1) {
            List<PyTerm> pyTerms2 = termList.get(x + 1);
            x++;

            List<PyTerm> list1 = Lists.newArrayList();
            for ( PyTerm pyTerm1 : pyTerms1 ) {
                for ( PyTerm pyTerm2 : pyTerms2 ) {
                    PyTerm pyTerm = PyTerm.copy(pyTerm1);
                    pyTerm.getList().addAll(pyTerm2.getList());
                    list1.add(pyTerm);
                }
            }
            pyTerms1 = list1;
        }
        return pyTerms1;
    }


    public static void main(String[] args) {
        System.out.println(new PySpliter().extractMostMatchPyListWithFilt("zhanghao12414xianh", true));
        String s = "zhang14zh";
        System.out.println(new PySpliter().getAllPyTerms(s));

    }
}
