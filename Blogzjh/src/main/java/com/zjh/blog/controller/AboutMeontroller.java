package com.zjh.blog.controller;

import com.zjh.blog.commons.PVFinalCount;
import com.zjh.blog.domain.Blogger;
import com.zjh.blog.domain.Message;
import com.zjh.blog.service.BlogService;
import com.zjh.blog.service.BlogTypeService;
import com.zjh.blog.service.BloggerService;
import com.zjh.blog.service.LinkService;
import com.zjh.blog.service.MessageService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/3/31 10:26
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class AboutMeontroller {

    private static final Logger log = LoggerFactory.getLogger(AboutMeontroller.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/about.html")
    public Object index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PVFinalCount.Count.incrementAndGet();
        //查询
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("state",1);
        List<Message> messageList = messageService.getMessageData(map);
        for (Message message : messageList){
            message.setAddress(message.getAddress() + "网友");
        }
        log.info("当前请求关于本站页面");
        ModelAndView modelAndView = new ModelAndView();
        Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute("blogger");
        if (blogger == null){
            //重定向去主页
            response.sendRedirect("/index/home.html");
            //返回ok
            return null;
        } else {
            modelAndView.addObject("messageList",messageList);
            modelAndView.setViewName("/indexViews/about");
        }
        return modelAndView;
    }
}
