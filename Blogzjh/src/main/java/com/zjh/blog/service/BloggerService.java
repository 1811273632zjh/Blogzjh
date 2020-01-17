package com.zjh.blog.service;

import com.zjh.blog.domain.Blogger;

/**
 * @Auther：zjh
 * @Description：博主service接口
 * @Data：2019/11/14 15:55
 * Version 1.0
 */
public interface BloggerService {

    /**
      * @Description: 查询博主信息
      */
    Blogger getBloggerData();

    /**
      * @Description: 根据用户名查询博主信息
      */
    Blogger getBloggerByName(Blogger blogger);

    /**
      * @Description: 更新博主信息
      * @Param: blogger
      * @return:
      */
    Integer updateBlogger(Blogger blogger);
}
