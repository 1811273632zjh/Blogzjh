package com.zjh.blog.service;

import com.zjh.blog.domain.Message;

import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2019/11/13 15:30
 * Version 1.0
 */
public interface MessageService {

    /**
      * @Description: 根据id查询
      * @Param: id
      * @return:
      */
    Message getById(Integer id);

    /**
      * @Description: 添加留言
      * @Param: message
      * @return:
      */
    Integer saveMessage(Message message);

    /**
      * @Description: 修改留言
      * @Param: message
      * @return:
      */
    Integer updateMessage(Message message);

    /**
      * @Description: 根据id删除留言
      * @Param: id
      * @return:
      */
    Integer deleteMessage(Integer id);

    /**
     * 获取总记录数目
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    List<Message> getMessageData(Map<String,Object> map);
}
