package com.zjh.blog.service;

import com.zjh.blog.domain.Blog;
import com.zjh.blog.domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：博客Service接口
 * @Data：2019/11/6 9:42
 * Version 1.0
 */
public interface BlogService {

    /**
      * @Description: 根据id查询
      * @Param: Integer id
      * @return: Blog
      */
    Blog getById(Integer id);

    /**
      * @Description: 新增博客
      * @Param: blog
      * @return: Blog
      */
    Integer saveBlog(Blog blog);

    /**
      * @Description: 修改博客
      * @Param: blog
      * @return: Blog
      */
    Integer updateBlog(Blog blog);

    /**
      * @Description: 删除博客
      * @Param: blog
      * @return: Blog
      */
    Integer deleteBlog(Integer id);

    /**
      * @Description: 根据日期月份分组查询
      * @Param: map
      * @return:
      */
    List<Blog> countList();

    /**
      * @Description: 分页查询博客
      * @Param: map
      * @return:
      */
    List<Blog> listBlog(Map<String, Object> map);

    /**
      * @Description: 分页查询博客
      * @Param: map
      * @return:
      */
    PageBean<Blog> listBlog(String title, PageBean<Blog> pageBean);

    /**
     * @Description: 获取总数
     * @Param: map
     * @return: long
     */
    long getTotal(Map<String, Object> map);
}
