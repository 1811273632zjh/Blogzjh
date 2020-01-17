package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.CommonMapper;
import com.zjh.blog.dao.MessageMapper;
import com.zjh.blog.domain.Message;
import com.zjh.blog.service.MessageService;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther：zjh
 * @Description：实现留言Service接口
 * @Data：2019/11/13 15:30
 * Version 1.0
 */
@Service
public class MessageServiceImpl extends CommonMapper implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message getById(Integer id) {
        return messageMapper.getById(id);
    }

    @Override
    public Integer saveMessage(Message message) {
        return messageMapper.saveMessage(message);
    }

    @Override
    public Integer updateMessage(Message message) {
        return messageMapper.updateMessage(message);
    }

    @Override
    public Integer deleteMessage(Integer id) {
        return messageMapper.deleteMessage(id);
    }
}
