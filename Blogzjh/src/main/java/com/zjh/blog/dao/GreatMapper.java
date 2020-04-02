package com.zjh.blog.dao;

import com.zjh.blog.domain.Great;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GreatMapper {
   
    /**
      * @Description:新增图片
      * @Param: record
      * @return:
      */
    Integer addGreat(Great record);
    /**
      * @Description: 删除图片
      * @Param: id
      * @return: 
      */
    int deleteGreat(Integer id);

    /**
      * @Description: 查看是否有imageId------>用户ip的关系
      * @Param: userip、imageid
      * @return: 
      */
    public Great getByUserIpAndImageId(@Param("userIp") String userIp,
                                       @Param("imageId") Integer imageId);

    /**
     * 根据imageid 删除所有对应的记录
     * @param id
     * @return
     */
    public Integer deleteGreatByImageId(Integer id);
}