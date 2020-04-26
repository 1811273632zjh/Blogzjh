package com.zjh.blog.commons;

import java.util.Comparator;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：TreeMap比较器，实现TreeMap可重复
 * @Data：2020/4/15 20:16
 * Version 1.0
 */
public class TreeMapComparatorForkinds implements Comparator<String> {

    Map<String, Integer> base;

    //这里需要将要比较的map集合传进来
    public TreeMapComparatorForkinds(Map<String, Integer> base){
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)){
            return -1;
        }
        if (base.get(a) < base.get(b)){
            return 1;
        } else {
            return 0;
        }
    }
}
