package com.zjh.blog.dao;

import com.zjh.blog.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    /**
      * @Description: 根据id 查询留言
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
      * @Description: 更新留言
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
      * @Description: 分页查询留言
      * @Param: map
      * @return:
      */
    List<Message> listByPage(Map<String, Object> map);

    /**
      * @Description: 获取总留言数
      * @Param: map
      * @return:
      */
    Long getTotal(Map<String, Object> map);

}