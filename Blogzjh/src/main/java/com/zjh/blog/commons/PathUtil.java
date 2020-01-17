package com.zjh.blog.commons;

/**
 * @Auther：zjh
 * @Description 获取项目存储的根路径
 * @Data：2019/11/7 11:00
 * Version 1.0
 */
public class PathUtil {

    /**
      * @Description: 获取项目根路径
      * @return:
      */
    public static String getRootPath(){
        String path = PathUtil.class.getResource("/").getPath();
        for (int i = 0;i < 3;i++){
            //返回最后一个出现索引的位置，即从右向左搜索，第一次出现“/”的位置，未找到返回 -1
            int end = path.lastIndexOf("/");
            //截取字符串，从下标0 开始，到返回的end 结束
            path = path.substring(0,end);
        }
        int index = path.indexOf(":");
        String path2 = path.substring(index + 1);
        return path;
        //return path2;         //不知道为什么要返回path2，结果：Workspace/IDEA/Blogszjh
    }

    public static void main(String[] args) {
        System.out.println(PathUtil.getRootPath().subSequence(1,PathUtil.getRootPath().length()));
    }
}
