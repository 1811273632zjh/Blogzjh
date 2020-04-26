package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.commons.MD5Util;
import com.zjh.blog.domain.Blogger;
import com.zjh.blog.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther：zjh
 * @Description：博主信息管理控制层
 * @Data：2019/11/14 11:26
 * Version 1.0
 */

@RestController
@RequestMapping(value = "/admin/blogger")
public class BloggerAdminController {

    private static  final Logger log = LoggerFactory.getLogger(BloggerAdminController.class);

    @Autowired
    private BloggerService bloggerService;

    /**
      * @Description: 获取博主信息
      * @Param: request
      */
    @ResponseBody
    @RequestMapping(value = "/getBlloggerInfo")
    public String getBloggerData(HttpServletRequest request){
        Blogger blogger = null;
        log.info("当前请求获取博主信息。。。");
        if (request.getSession().getAttribute("currentUser") != null){
            blogger = (Blogger) request.getSession().getAttribute("currentUser");
        }else{
            blogger = bloggerService.getBloggerData();
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("blogger",blogger);
        return result.toJSONString();
    }

    /**
      * @Description: 更新博主信息
      * @Param: blogger
      * @return:
      */
    @ResponseBody
    @RequestMapping(value = "/save")
    public String saveBlogger(Blogger blogger){
        log.info("当前请求更新信息。。。");
        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if(resultTotal > 0){
            result.put("success",true);
        }else {
            result.put("blogger",blogger);
        }
        return result.toJSONString();
    }

    /**
      * @Description: 更新博主密码
      * @Param: password,token
      * @return: newpassword
      */
    @ResponseBody
    @RequestMapping(value = "/moditfyPassword")
    public String moditfyBloggerPassword(Blogger blogger,String token){
        log.info("当前请求修改密码。。。");
        JSONObject result = new JSONObject();
        //对token密码进行加密
        if(token == null || !MD5Util.TOKEN_FOR_MODIFYPASSWORD.equals(token)){
            System.out.println("token："+token);
            result.put("success", -1);
            return result.toJSONString();
        }
        log.info("正在更新密码。。。加密前密码："+ blogger.getPasswoed());
        String newPassword = MD5Util.md5(blogger.getPasswoed(),blogger.getUsername());
        blogger.setPasswoed(newPassword);
        /* 此处是加密后用户名和密码来保存 */
        int resultTotal = bloggerService.updateBlogger(blogger);
        log.info("更新后MD5加密后的密码："+newPassword);
        if(resultTotal > 0){
            result.put("success",1);
        }else{
            result.put("success",0);
        }
        return result.toJSONString();
    }

    /**
      * @Description: 注销（退出）功能
      * @Param:
      * @return:
      */
    @ResponseBody
    @RequestMapping(value = "/logout")
    public String logout(){
        log.info("已退出后台系统");
        SecurityUtils.getSubject().logout();
        return "redirect:/login.html";
    }
}
