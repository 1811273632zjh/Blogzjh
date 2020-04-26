package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.BlogTypeMapper;
import com.zjh.blog.domain.BlogType;
import com.zjh.blog.domain.PageBean;
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
    public PageBean<BlogType> listByPage(PageBean<BlogType> pageBean) {
        // 查询分页结果
        pageBean.setResult(blogTypeMapper.listByPage(pageBean.getStart(),
                pageBean.getEnd()));
        // 查询记录总数
        pageBean.setTotal(blogTypeMapper.getTotal());
        return pageBean;
    }

    @Override
    public Integer addBlogType(BlogType blogType) {
        return blogTypeMapper.addBlogType(blogType);
    }

    @Override
    public Integer updateBlogType(BlogType blogType) {
        return blogTypeMapper.updateBlogType(blogType);
    }

    @Override
    public Integer deleteBlogType(Integer id) {
        return blogTypeMapper.deleteBlogType(id);
    }

    @Override
    public List<BlogType> getBlogTypeData() {
        // TODO 自动生成的方法存根
        return blogTypeMapper.getBlogTypeData();
    }
}
