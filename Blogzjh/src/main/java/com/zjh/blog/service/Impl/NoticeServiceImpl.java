package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.NoticeMapper;
import com.zjh.blog.domain.Notice;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/1/17 11:43
 * Version 1.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Integer deleteNoticeById(Integer id) {
        return null;
    }

    @Override
    public Integer saveNotice(Notice notice) {
        return null;
    }

    @Override
    public Integer updateNotice(Notice notice) {
        return null;
    }

    @Override
    public PageBean<Notice> listNotice(PageBean<Notice> pageBean) {
        return null;
    }

    @Override
    public Long getTotal() {
        return null;
    }

    @Override
    public List<Notice> getAllNotices() {
        return null;
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return null;
    }
}
