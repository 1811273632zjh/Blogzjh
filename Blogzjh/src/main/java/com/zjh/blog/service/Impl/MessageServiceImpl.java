package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.CommentMapper;
import com.zjh.blog.dao.MessageMapper;
import com.zjh.blog.domain.Message;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：实现留言Service接口
 * @Data：2019/11/13 15:30
 * Version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public PageBean<Message> listByPage(PageBean<Message> pageBean) {
        pageBean.getMap().put("start", pageBean.getStart());
        pageBean.getMap().put("end", pageBean.getEnd());
        pageBean.setResult(messageMapper.listByPage(pageBean.getMap()));
        pageBean.setTotal(messageMapper.getTotal(pageBean.getMap()));
        return pageBean;
    }

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

    @Override
    public Long getTotal(Map<String, Object> map) {
        return messageMapper.getTotal(map);
    }

    @Override
    public List<Message> getMessageData(Map<String, Object> map) {
        return messageMapper.listByPage(map);
    }
}
