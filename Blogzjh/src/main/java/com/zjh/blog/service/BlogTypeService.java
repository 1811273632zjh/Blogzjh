package com.zjh.blog.service;

import com.zjh.blog.domain.BlogType;
import com.zjh.blog.domain.PageBean;

import java.util.List;

/**
 * @Auther：zjh
 * @Data：2019/11/4 23:36
 * Version 1.0
 */
public interface BlogTypeService {

    /**
     * @Description 分页查询
     * @param pageBean
     * @return
     */
    PageBean<BlogType> listByPage(PageBean<BlogType> pageBean);

    /**
     * @Description 添加博客类别
     * @param blogType
     * @return
     */
    public Integer addBlogType(BlogType blogType);

    /**
     * @Description 更新博客类别
     * @param blogType
     * @return
     */
    public Integer updateBlogType(BlogType blogType);

    /**
     * @Description 删除博客类别
     * @param id
     * @return
     */
    public Integer deleteBlogType(Integer id);

    /**
     * @Description 获取博客类型列表
     * @return
     */
    List<BlogType> getBlogTypeData();
}
