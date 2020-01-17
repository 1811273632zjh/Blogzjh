package com.zjh.blog.dao;

import com.zjh.blog.domain.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {

    //根据id查询
    Blog getById(Integer id);

    //添加博客
    Integer saveBlog(Blog blog);

    //修改博客
    Integer updateBlog(Blog blog);

    //删除博客
    Integer deleteBlog(Integer id);

    //根据日期月份分组查询
    List<Blog> countList();

    //分页查询博客
    List<Blog> listBlog(Map<String, Object> map);

    //获取总记录数
    Integer getTotal(Map<String, Object> map);

    //根据博客类型的id 查询该类型下的博客
    Integer getBlogTypeId(Integer typeId);


}