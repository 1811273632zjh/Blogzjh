package com.zjh.blog.controller;


import com.alibaba.druid.util.StringUtils;
import com.zjh.blog.commons.AddressUtils;
import com.zjh.blog.commons.MD5Util;
import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.Blogger;
import com.zjh.blog.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：博主控制层前台，不需要shiro认证
 * @Data：2020/4/25 9:37
 * Version 1.0
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
    private static final Logger log = (Logger) LoggerFactory.getLogger(BloggerController.class);

    @Resource
    private BlogService blogService;
    @Resource
    private BloggerService bloggerService;
    @Resource
    private BlogTypeService blogTypeService;
    @Resource
    private LinkService linkService;
    @Resource
    private MessageService messageService;
    @Autowired
    private CommentService commentService;
    @Autowired(required=false)
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(Blogger blogger, HttpServletRequest request) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        log.info("加密前用户名：" + blogger.getUsername() + "加密前密码：" + blogger.getPasswoed());
        //获取加密后密码
        String password = MD5Util.md5(blogger.getPasswoed(),blogger.getUsername());
        //获取用回顾密码登录token， 将需要验证的用户名和密码的md5值传给myrealm
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(),password);

        try{
            //根据token登录，会调用myrealm中的doGetAythenticationInfo 方法进行身份验证
            subject.login(token);
            log.info("验证密码成功。。。。");
            String code = request.getParameter("code");  //得到验证码
            //得到后台session的验证码
            HttpSession session = request.getSession();
            String sessionCode = (String) redisTemplate.opsForValue().get("code");
            log.info("shiro控制的session ---->当前session超时=" + session.getMaxInactiveInterval() + "s");
            if (!StringUtils.equalsIgnoreCase(code,sessionCode)){
                log.info("验证码对应不上code=" + code + "redisCode=" + sessionCode);
                request.setAttribute("errorInfo","验证码错误");
                return "index";
            }
            log.info("*******************分界线*********************");
            //获取博客总数量
            Map<String, Object> map = new HashMap<String, Object>();
            long articleCount = blogService.getTotal(map);
            //获取博客总访问量
            Map<String, Object> mapBlogClick = new HashMap<String, Object>();
            List<Blog> blogClick = blogService.listBlog(mapBlogClick);
            int blogClickCount = 0;
            for (Blog blog : blogClick){
                blogClickCount += blog.getClickhit();
            }

            String ip = AddressUtils.getRealIp(request);
            log.info("博主:ip=" + ip);
            String address = AddressUtils.getAddress("ip=" + ip, "utf-8");  //地址
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();     //登录的时间
            String str = sdf.format(date);
            ServletContext application = RequestContextUtils.findWebApplicationContext(request).getServletContext();
            Map<String, Object> map2 = new HashMap<>();
            Map<String, Object> map3 = new HashMap<>();
            map2.put("state", 0);       //待审核
            Long messageCount0 = messageService.getTotal(map2);
            Long messageCount = messageService.getTotal(map3);  //总留言
            application.setAttribute("articleCount", articleCount);
            application.setAttribute("blogClickCount", blogClickCount);
            application.setAttribute("messageCount", messageCount);
            application.setAttribute("ip", ip);
            application.setAttribute("address", address);
            application.setAttribute("str", str);
            return "main";
        } catch (UnknownAccountException e){
            e.printStackTrace();
            request.setAttribute("errorInfo","用户名或密码错误！");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            request.setAttribute("errorInfo","用户名或密码错误！");
        } catch (ExcessiveAttemptsException e){
            e.printStackTrace();
            request.setAttribute("errorInfo","登录失败次数过多，请稍后再试！");
        }

        return "index";
    }


}
