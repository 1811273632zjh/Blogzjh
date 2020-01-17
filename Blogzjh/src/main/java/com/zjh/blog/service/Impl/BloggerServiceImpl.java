package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.BloggerMapper;
import com.zjh.blog.domain.Blogger;
import com.zjh.blog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther：zjh
 * @Description：博主service实现类
 * @Data：2019/11/14 15:55
 * Version 1.0
 */
@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerMapper bloggerMapper;

    @Override
    public Blogger getBloggerData() {
        return bloggerMapper.getBlogggerData();
    }

    @Override
    public Blogger getBloggerByName(Blogger blogger) {
        return bloggerMapper.getBloggerByName(blogger.getUsername());
    }

    @Override
    public Integer updateBlogger(Blogger blogger) {
        return bloggerMapper.updateBlogger(blogger);
    }
}
