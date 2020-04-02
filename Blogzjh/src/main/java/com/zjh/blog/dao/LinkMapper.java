package com.zjh.blog.dao;


import com.zjh.blog.domain.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkMapper {
    
    /**
      * @Description: 查询所有的友链信息
      * @Param: 
      * @return: 
      */
    List<Link> getTotalData();
    
    /**
      * @Description: 对友链信息进行分页
      * @Param: 
      * @return: 
      */
    List<Link> listByPage(Integer start, Integer end);

    /**
      * @Description: 获取suoyou
      * @Param: 
      * @return: 
      */
    Long getTotalCount();

    /**
      * @Description: 删除友链
      * @Param: id
      * @return:
      */
    int deleteLink(Integer id);

    /**
      * @Description: 添加友链
      * @Param: link
      * @return:
      */
    int addLink(Link link);

    /**
      * @Description: 更新友链
      * @Param: link
      * @return:
      */
    int updateLink(Link link);

}