package com.project.util;

import java.util.*;

public class Sort {
//    public static void main(String[] args) {
//        Map map = new HashMap();
//        map.put(2,2);
//        map.put(3,5);
//        map.put(1,3);
//        map.put(6,2);
//        List<Map.Entry<Integer,Integer>> list = Sort.sortByValueFloatDesc(map);
//        System.out.println(list.get(1).getValue());
//    }

    public static  List<Map.Entry<Integer,Integer>> sortByValueFloatDesc(Map<Integer, Integer> nowPartTwoData){
        //这里将map.entrySet()转换成list
List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(nowPartTwoData.entrySet());
        //然后通过比较器来实现排序
         list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
             //降序排序
             @Override
             public int compare(Map.Entry<Integer, Integer> o1,
                                Map.Entry<Integer, Integer> o2) {
                 return o2.getValue().compareTo(o1.getValue());
             }
         });

        return list;
       }
}
