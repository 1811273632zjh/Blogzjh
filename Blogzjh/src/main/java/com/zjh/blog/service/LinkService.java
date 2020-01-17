package com.zjh.blog.service;

import com.zjh.blog.domain.Link;
import com.zjh.blog.domain.PageBean;

import java.util.List;

/**
 * @Auther：zjh
 * @Description：友链Service接口
 * @Data：2019/12/5 15:16
 * Version 1.0
 */
public interface LinkService {

    /**
      * @Description: 查询所有友链
      * @return:
      */
    List<Link> getTotalData();

    /**
      * @Description: 分页
      * @Param:
      * @return:
      */
    PageBean<Link> listByPage(PageBean<Link> pageBean);

    /**
      * @Description: 获取友链总数
      * @Param:
      * @return: long
      */
    Long getTotalCount();

    /**
      * @Description: 添加友链
      * @Param: link
      * @return:
      */
    Integer addLink(Link link);

    /**
      * @Description: 修改友链
      * @Param: link
      * @return:
      */
    Integer updateLink(Link link);

    /**
      * @Description: 删除友链
      * @Param: link
      * @return:
      */
    Integer deleteLink(Integer id);

}
