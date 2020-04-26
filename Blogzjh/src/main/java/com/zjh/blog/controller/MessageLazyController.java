package com.zjh.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjh.blog.domain.Message;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther：zjh
 * @Description：留言板的懒加载分页
 * @Data：2020/4/25 17:25
 * Version 1.0
 */
@RestController
@RequestMapping(value = "/blog/messageLazy")
public class MessageLazyController {

    private static final Logger log = LoggerFactory.getLogger(MessageLazyController.class);

    @Autowired
    private MessageService messageService;

    public String listMessage(@RequestParam(value = "page", required = false) String page,
                              HttpServletRequest request) {
        log.info("当前正在请求留言懒加载页面。。。。");
        PageBean<Message> pageBean = new PageBean<>(Integer.parseInt(page), 10);
        pageBean.getMap().put("state",1);
        pageBean = messageService.listByPage(pageBean);
        System.out.println("start：" + pageBean.getStart() + "，end：" + pageBean.getEnd());
        JSONObject result = new JSONObject();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String jsonStr = JSONObject.toJSONString(pageBean.getResult(), SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        result.put("count", pageBean.getCount());
        result.put("total", pageBean.getTotal());
        result.put("currentPage", pageBean.getCurrPage());
        result.put("data", jsonArray);
        result.put("code", 0);      //封装接口，成功返回0

        return result.toJSONString();
    }

}
