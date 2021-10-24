package com.ferry.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则提取
 *
 * @author
 */
public class RegexUtil {
    /**
     * ${ [A-Za-z1-9_-]* } $大括号间 字母，数字，下划线，减号
     */
    public static String REGEX_$BRACE = "(\\$\\{[A-Za-z1-9_-]*\\})";
    /**
     * ${ [^\\}]* } $大括号间任意任意字符
     */
    public static String REGEX_$BRACE_ANY = "(\\$\\{[^\\}]*\\})";

    /**
     * {{ [A-Za-z1-9_.-]* }} $大括号间任意任意字符 带点
     */
    public static String REGEX_DBL_BRACE = "(\\{\\{[A-Za-z1-9_.-]*\\}\\})";

    /**
     * {{ [^\\}]* }} $大括号间任意任意字符
     */
    public static String REGEX_DBL_BRACE_ANY = "(\\{\\{[^\\}]*\\}\\})";

    /**
     * [ [A-Za-z1-9_-] ] 中括号间 字母，数字，下划线，减号
     */
    public static String REGEX_BRACKET = "(\\[[A-Za-z1-9_-]*\\])";
    /**
     * [ [^\\}]* ] 中括号间任意任意字符
     */
    public static String REGEX_BRACKET_ANY = "(\\[[^\\]]*\\])";

    /**
     * 使用正则表达式提取中括号中的内容
     *
     * @param msg
     * @param regexStr 正则表达式
     * @return
     */
    public static List<String> extractContentByRegular(String msg, String regexStr) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(regexStr);
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 正则替换  \\$\\{key\\}
     *
     * @param content
     * @param map
     * @return
     */
    public static String renderString(String content, Map<String, String> map) {
        Set<Entry<String, String>> sets = map.entrySet();
        for (Entry<String, String> entry : sets) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            content = matcher.replaceAll(entry.getValue());
        }
        return content;
    }

    /**
     * 正则替换(中括号的内容)  \\{\\{key\\}\\}
     *
     * @param content
     * @param map
     * @return
     */
    public static String renderDblBraceString(String content, Map<String, String> map) {
        Set<Entry<String, String>> sets = map.entrySet();
        for (Entry<String, String> entry : sets) {
            String regex = "\\{\\{" + entry.getKey() + "\\}\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            content = matcher.replaceAll(entry.getValue());
        }
        return content;
    }

    /**
     * 正则替换(中括号的内容)  \\{\\{key\\}\\}
     *
     * @param content
     * @return
     */
    public static List<Map<String, String>> renderELAnyString(String content) {
        String s1 = content.replaceAll("\\$", "").replaceAll("\\{", "").replaceAll("}", "");
        Map<String, String> map = new HashMap<String, String>();
        map.put(">", ",");
        map.put("<", ",");
        map.put(">=", ",");
        map.put("<=", ",");
        map.put("==", ",");
        map.put("and", "@");
        map.put("or", "@");
        Set<Entry<String, String>> sets = map.entrySet();
        for (Entry<String, String> entry : sets) {
            String regex = entry.getKey();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s1);
            s1 = matcher.replaceAll(entry.getValue());
        }
        List<Map<String, String>> res = new ArrayList<>();
        if (s1.contains("@")) {
            String[] split = s1.split("@");
            for (String s : split) {
                if (s.contains(",")) {
                    String[] splitChild = s.split(",");
                    Map<String, String> hashMap = Maps.newHashMap();
                    hashMap.put(splitChild[0].trim(), splitChild[1].trim());
                    res.add(hashMap);
                }
            }
        } else {
            String[] split = s1.split(",");
            Map<String, String> hashMap = Maps.newHashMap();
            hashMap.put(split[0].trim(), split[1].trim());
            res.add(hashMap);
        }

        return res;
    }

    /**
     * 提取 字符见内容
     *
     * @param msg
     * @return
     */
    public static List<String> extractContent(String msg, char left, char right) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == left) {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == right) {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        return list;
    }

    /**
     * 替换字符串中的正则表达式
     *
     * @param targetStr 目标字符串
     * @param condition 条件json字符串
     * @return
     */
    public static String replaceEL(String targetStr, Map condition) {
        if (StringUtils.isEmpty(targetStr) || null == condition) {
            return targetStr;
        }
        for (Object key : condition.keySet()) {
            targetStr = targetStr.replace("${" + key + "}", String.valueOf(condition.get(key)));
        }
        return targetStr;
    }
}
