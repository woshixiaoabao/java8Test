package com.abao.java8.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/11/27 10:41
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
//        for (String str : list) {
//            if ("bb".equals(str)) {
//                list.remove(str);
//            }
//        }
//        for (int i = 0; i < list.size(); i++) {
//            String ss = list.get(i);
//            if ("bb".equals(ss)) {
//                list.remove(ss);
//            }
//        }
        List<String> longList = new ArrayList<>();
        for (String str: list) {
            longList.add(str);
        }
        longList.stream().forEach(tt-> System.out.println(tt));
    }
}
