package com.zjh.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.zjh.blog.domain.Message;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther：zjh
 * @Description：留言访问控制层
 * @Data：2019/11/13 15:27
 * Version 1.0
 */

@RestController
@RequestMapping(value = "admin/message")
public class MessageAdminController {

    private static final Logger log = LoggerFactory.getLogger(MessageAdminController.class);

    @Autowired
    private MessageService messageService;

    //根据id 查询
    //添加
    //修改

    //删除
    @RequestMapping(value = "/delete")
    public String deleteMessage(String ids){
        log.info("当前请求删除留言");
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++){
            messageService.deleteMessage(Integer.parseInt(idStr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        return result.toJSONString();
    }

    /**
      * @Description: 审核留言
      * @Param: id，state
      * @return: success
      */
    @RequestMapping(value = "review")
    public String reviewMessage(String ids,String state){
        log.info("当前请求审核留言");
        String[] idStr = ids.split(",");
        for (int i = 0;i < idStr.length; i++){
            Message message = new Message();
            message.setId(Integer.parseInt(idStr[i]));
            message.setState(Integer.parseInt(state));
            messageService.updateMessage(message);
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        return result.toJSONString();
    }

    /**
      * @Description: 分页留言
      * @Param: page，limit，state
      * @return:
      */
    public String listByPage(String page,String limit,String state){
        log.info("当前请求分页留言功能。。。");
        PageBean<Message> pageBean = new PageBean<Message>(Integer.parseInt(page),Integer.parseInt(state));
        pageBean.getMap().put("state",state);

        return null;
    }
}
