package com.zjh.blog.dao;


import com.zjh.blog.domain.BlogType;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface BlogTypeMapper {

    int delete(Integer id);

    Integer insert(BlogType blogType);

    Integer update(BlogType blogType);

    List<BlogType> selectById(Integer id);

    int updateByPrimaryKeySelective(BlogType record);

    int updateByPrimaryKey(BlogType record);
}