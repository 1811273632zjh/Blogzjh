package com.zjh.blog.service;

import com.zjh.blog.domain.BlogType;

import java.util.List;

/**
 * @Auther：zjh
 * @Data：2019/11/4 23:36
 * Version 1.0
 */
public interface BlogTypeService {

    List<BlogType> selectById(Integer id);

    Integer update(BlogType blogType);

    Integer insert(BlogType blogType);

    Integer delete(Integer id);
}
