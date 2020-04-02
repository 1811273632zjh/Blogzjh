package com.zjh.blog.commons;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther：zjh
 * @Description：工具类，读入，写入count
 * @Data：2020/2/26 10:01
 * Version 1.0
 */
public class PVFinalCount {

    public static AtomicLong Count = null;

    public static void writeFileString(Long count) throws IOException {
        File file = new File("E:\\web\\PV");
        if(!file.isDirectory()){
            file.mkdir();   //创建目录
        }
        File fileDir = new File(file,"pv.text");
        if(!fileDir.isFile()){
            try{
                fileDir.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileDir);   //FileWriter写入文件时不能指定编码格式，只能是系统默认的编码格式
            fileWriter.write(String.valueOf(count));//向文件中写入字符串
            fileWriter.flush();                     //刷新
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();                     //关闭流
        }
    }

    public static Long redeFileString() throws IOException {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        String string = null;
        try {
            fileReader = new FileReader("E:\\web\\PV\\pv.text");    //字符串读入流
            bufferedReader = new BufferedReader(fileReader);
            string = bufferedReader.readLine();         //读入数据
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
            bufferedReader.close();
        }
        return Long.parseLong(string);
    }
}
