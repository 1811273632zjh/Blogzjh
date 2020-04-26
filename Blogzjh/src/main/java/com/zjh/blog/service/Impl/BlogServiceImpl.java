package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.BlogMapper;
import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：博客Service实现类
 * @Data：2020/4/15 20:05
 * Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getById(Integer id) {
        return blogMapper.getById(id);
    }

    @Override
    public Integer saveBlog(Blog blog) {
        return blogMapper.saveBlog(blog);
    }

    @Override
    public Integer updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public Integer deleteBlog(Integer id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> countList() {
        return blogMapper.countList();
    }

    @Override
    public List<Blog> listBlog(Map<String, Object> map) {
        return blogMapper.listBlog(map);
    }

    @Override
    public PageBean<Blog> listBlog(String title, PageBean<Blog> pageBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置查询条件
        map.put("title", title);
        // 总记录放入pageBean
        pageBean.setTotal(blogMapper.getTotal(map));
        map.put("start", pageBean.getStart());
        map.put("end", pageBean.getEnd());
        // 把分页结果放入pageBean
        pageBean.setResult(blogMapper.listBlog(map));
        return pageBean;
    }

    @Override
    public long getTotal(Map<String, Object> map) {
        return blogMapper.getTotal(map);
    }
}
