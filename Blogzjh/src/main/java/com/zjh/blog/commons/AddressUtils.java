package com.zjh.blog.commons;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Auther：zjh
 * @Description：查询ip地址
 * @Data：2020/4/1 15:02
 * Version 1.0
 */
public class AddressUtils {

    public static void main(String[] args) {
        String ip = "118.212.202.202";
        String address = "";
        try {
            address = AddressUtils.getAddress("ip = " + ip,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(address);

    }

    /**
      * @Description: 获取地址
      * @Param: params、encoding
      * @return:
      */
    public static String getAddress(String params ,String encoding) throws Exception {
        String path = "http://ip.taobao.com/service/getIpInfo.php";
        //String path = "http://ip.ws.126.net/ipquery";
        String returnStr = getRs(path, params, encoding);   //获取返回的结果
        JSONObject json = null;
        if (returnStr != null){
            json = new JSONObject(returnStr);

            if ("0".equals(json.get("code").toString())){
                StringBuffer buffer = new StringBuffer();
                buffer.append(decodeUnicode(json.optJSONObject("data").getString("country")));  //国家
                buffer.append(decodeUnicode(json.optJSONObject("data").getString("region"))); //省份
                buffer.append(" ");
                buffer.append(decodeUnicode(json.optJSONObject("data").getString("city")));     //市区
                String s = buffer.toString();
                String[] ss = s.split(" ");
                String scountry = null;

                @SuppressWarnings("unused")
                String sregion = null;
                String scity = null;
                sregion = ss[0];
                scity = ss[1];
                if ("内网ip".equals(scountry) || "内网ip".equals(scity)){
                    return "内网ip";
                }
                return buffer.toString();
            } else {
                return "匿名";
            }
        }
        return "匿名";
    }

    /**
      * @Description: 从url中获取结果
      * @Param: path  params encoding
      * @return:
      */
    public static String getRs(String path, String params,String encoding){
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();  //新建连接实例
            connection.setConnectTimeout(3000);     //设置连接数超时时间，单位 毫秒
            connection.setDoInput(true);            //是否打开输出流 true| false
            connection.setDoOutput(true);           //是否打开输入流 true| false
            connection.setRequestMethod("GET");     //提交方式为POST | GET
            connection.setUseCaches(false);         //是否打开缓存 true| false
            connection.connect();                   //打开连接端口

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();    //关闭连接
        }
        return null;
    }

    /**
      * @Description: 字符转码
      * @Param: theString
      * @return:
      */
    public static String decodeUnicode(String theString) throws IllegalAccessException {
        char aChar;
        int len = theString.length();
        StringBuffer buffer = new StringBuffer();
        for (int i= 0 ;i < len;){
            aChar = theString.charAt(i++);
            if (aChar == '\\'){
                aChar = theString.charAt(i++);
                if(aChar == 'u'){
                    int val = 0;
                    for (int j = 0;j < 4;j++){
                        aChar = theString.charAt(i++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                val = (val << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                val = (val << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                val = (val << 4) + 10 + aChar - 'A';
                                break;
                                default:
                                    throw new IllegalAccessException("Malformed    encoding.");
                        }
                    }
                    buffer.append((char) val);
                } else {
                    if (aChar == 't'){
                        aChar = '\t';
                    }
                    if (aChar == 'r'){
                        aChar = '\r';
                    }
                    if (aChar == 'n'){
                        aChar = '\n';
                    }
                    if (aChar == 'f'){
                        aChar = '\f';
                    }
                    buffer.append(aChar);
                }
            } else {
                buffer.append(aChar);
            }
        }
        return buffer.toString();
    }

    public static String getRealIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("come from x-forwarded-for "+ ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("HTTP_CLIENT_IP");
                System.out.println("ip HTTPCLIENT_IP " + ip);
            }
            if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)){
                ip = request.getHeader("X-Real-IP");
                System.out.println("ip form real " + ip);
            }
            if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
                System.out.println("ip from addr " + ip);
            }
        } else if (ip.length() > 15){
            String[] ips = ip.split(".");
            for (int index = 0; index < ips.length; index++ ){
                String strIp = ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    System.out.println("ip come come form x-frowarded-fors " + ip);
                    break;
                }
            }
        }
        return ip;
    }
}
