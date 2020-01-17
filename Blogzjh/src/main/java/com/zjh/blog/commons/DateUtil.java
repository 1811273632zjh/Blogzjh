package com.zjh.blog.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther：zjh
 * @Description：日期工具类
 * @Data：2019/11/9 14:11
 * Version 1.0
 */
public class DateUtil {

    /**
      * @Description: 日期对象转换字符串
      * @Param: data
      * @return: resule
      */
    public static String formatDate(Date date,String formate){
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        if(date != null){
            result = sdf.format(date);
        }
        return result;
    }

    /**
      * @Description: 按照yyytMMddhhmmss 格式获取当前时间
      * @Param:
      * @return: date
      */
    public static String getCurrentDateStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
