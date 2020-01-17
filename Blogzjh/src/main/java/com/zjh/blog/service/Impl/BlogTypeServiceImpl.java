package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.BlogTypeMapper;
import com.zjh.blog.domain.BlogType;
import com.zjh.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther：zjh
 * @Data：2019/11/4 23:37
 * Version 1.0
 */

@Service
public class BlogTypeServiceImpl implements BlogTypeService {
    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    public List<BlogType> selectById(Integer id) {
        List<BlogType> blogTypes = blogTypeMapper.selectById(id);
        //System.out.println(blogTypes);
        return blogTypes;
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeMapper.update(blogType);
    }

    @Override
    public Integer insert(BlogType blogType) {
        if(blogType.getTypeName() == null){
            System.out.println("博客名称不能为空");
        }
        return blogTypeMapper.insert(blogType);
    }

    @Override
    public Integer delete(Integer id) {

        return blogTypeMapper.delete(id);
    }
}
