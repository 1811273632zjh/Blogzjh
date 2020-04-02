package com.zjh.blog.dao;

import com.zjh.blog.domain.Notice;
import net.sf.ehcache.search.expression.Not;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：公告
 * @Data：2020/1/17 11:46
 * Version 1.0
 */
@Repository
public interface NoticeMapper {

    /**
      * @Description: 根据id删除
      * @Param: integer
      * @return: int
      */
    Integer deleteNoticeById(Integer id);

    /**
      * @Description: 保存公告
      * @Param: notice
      * @return: integer
      */
    Integer saveNotice(Notice notice);

    /**
      * @Description: 修改公告
      * @Param: notice
      * @return: integer
      */
    Integer updateNotice(Notice notice);

    /**
      * @Description: 分页获取公告，后台调用该接口
      * @Param: 
      * @return: 
      */
    List<Notice> listNotice(Map<String,Object> map);

    /**
      * @Description: 查询公告总数
      * @Param: long
      * @return: long
      */
    Long getTotal();

    /**
      * @Description: 获取所有的公告总数，前台调用该接口
      * @Param:
      * @return: String
      */
    List<Notice> getAllNotices();

    /**
      * @Description: 根据id获取公告信息
      * @Param: integer
      * @return: string
      */
    Notice getNoticeById(Integer id);
}
