package com.zjh.blog.commons;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Auther：zjh
 * @Description：MD5加密工具类
 * @Data：2019/12/3 10:03
 * Version 1.0
 */
public class MD5Util {
    
    public static String TOKEN_FOR_MODIFYPASSWORD = "ce6760509794de149c3fc8fa65a881a7";

    /**
      * @Description: 使用shiro 中的 md5 加密
      * @Param: 
      * @return: 
      */
    public static  String md5(String str,String salt){
        return  new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")     //有参数未被使用，移除上面上的警告
        String password1 = MD5Util.md5("admin","admin");
        String password = MD5Util.md5("admin","admin");
        System.out.println("加密后密文：" + password);

    }
}
