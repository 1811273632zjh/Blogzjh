package com.zjh.blog.controller;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.commons.AddressUtils;
import com.zjh.blog.commons.StringUtil;
import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.Message;
import com.zjh.blog.service.BlogService;
import com.zjh.blog.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther：zjh
 * @Description：前台评论控制器
 * @Data：2020/4/25 17:25
 * Version 1.0
 */
@RestController
@RequestMapping(value = "/blog/message")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    private BlogService blogService;
    @Autowired(required = false)
    private RedisTemplate<String, String> redisTemplate;

    //评论更新或添加
    @RequestMapping(value = "save")
    public String save(Message message, HttpServletRequest request,
                       HttpServletResponse response, HttpSession session,
                       @RequestParam(value = "code", required = true) String code,
                       @RequestParam(value = "email", required = true) String email) throws Exception{
        //四位数的String格式
        JSONObject result = new JSONObject();
        //得到后台session的验证码
        String sessionCode = redisTemplate.opsForValue().get("code");
        log.info("当前redisCode过期时间：" + redisTemplate.getExpire("code"));
        if (!StringUtils.equalsIgnoreCase(code, sessionCode)){      //忽略验证码大小写
            log.info("验证码对应不上code：" + code + "，redosCode：" + sessionCode);
            result.put("success",false);
            result.put("errorInfo","验证码错误！！！");
            return result.toJSONString();
        } else {
            int resultTotal = 0;    //执行记录数
            //获取评论ip
            String ip = AddressUtils.getRealIp(request);
            String address = AddressUtils.getAddress("ip=" + ip, "utf-8");
            log.info("留言者：ip=" + ip);
            message.setUserIp(ip);
            message.setAddress(address);
            message.setState(0);    //默认设置为0，待审核
            if (message.getId() == null){
                resultTotal = messageService.saveMessage(message);      //添加留言
                // Blog blog = blogService.getById(comment.getBlog().getId());
                // // 更新一下博客的评论次数
                // blog.setReplyHit(blog.getReplyHit() + 1);//在这里不加1
                // blogService.updateBlog(blog);
            } else {
                //更新操作，暂时不使用
            }

            if (resultTotal > 0){
                result.put("success",true);
            }
            return result.toJSONString();
        }
    }
}
