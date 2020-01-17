package com.zjh.blog.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：zjh
 * @Description：字符串工具类
 * @Data：2019/11/9 22:55
 * Version 1.0
 */
public class StringUtil {

    /**
      * @Description: 判断是否为空
      * @Param: str
      * @return: true 或 false
      */
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }

    /**
      * @Description: 判断是否不是空
      * @Param: str
      * @return: boolean类型：true 或 false
      */
    public static boolean isNotEmpty(String str){
        if((str != null) && !"".equals(str.trim())){
            return true;
        }else {
            return false;
        }
    }

    /**
      * @Description: 过滤掉集合中的空格
      * @Param: list
      * @return: resultList
      */
    public static List<String> filterWhite(List<String> list){
        List<String> resultList = new ArrayList<String>();
        for(String l : list){
            if (isNotEmpty(l)){
                resultList.add(l);
            }
        }
        return resultList;
    }
}
