package com.zjh.blog.service;

import com.zjh.blog.domain.Notice;
import com.zjh.blog.domain.PageBean;
import net.sf.ehcache.search.expression.Not;

import java.util.List;

/**
 * @Auther：zjh
 * @Description：公告服务层接口
 * @Data：2020/1/17 11:23
 * Version 1.0
 */
public interface NoticeService {
    /**
      * @Description: 根据id删除公告
      * @Param: integer
      * @return: int
      */
    Integer deleteNoticeById(Integer id);

    /**
      * @Description:保存公告
      * @Param: integer
      * @return: int
      */
    Integer saveNotice(Notice notice);

    /**
      * @Description: 修改公告
      * @Param: integer
      * @return: int
      */
    Integer updateNotice(Notice notice);

    /**
      * @Description: 分页获取公告，后天调用该接口
      * @Param: map
      * @return: map
      */
    PageBean<Notice> listNotice(PageBean<Notice> pageBean);

    /**
      * @Description: 获取总公告数量
      * @Param: long
      * @return: int
      */
    Long getTotal();

    /**
      * @Description: 获取所有公告数据，前台使用该接口
      * @Param: list
      * @return: list
      */
    List<Notice> getAllNotices();

    /**
      * @Description: 根据id获取查询
      * @Param: integer
      * @return: String
      */
    Notice getNoticeById(Integer id);




}
