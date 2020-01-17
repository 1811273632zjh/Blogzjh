package com.zjh.blog.dao;

import com.zjh.blog.domain.Blogger;
import org.springframework.stereotype.Repository;

/**
  * @Description: 博主dao接口
  */
@Repository
public interface BloggerMapper {

    /**
      * @Description: 查询博主信息
      */
    Blogger getBlogggerData();

    /**
      * @Description: 通过用户名查询博主信息
      * @Param: username
      * @return:
      */
    Blogger getBloggerByName(String username);

    /**
      * @Description: 更新博主信息
      * @Param: blogger
      * @return:
      */
    Integer updateBlogger(Blogger blogger);
}