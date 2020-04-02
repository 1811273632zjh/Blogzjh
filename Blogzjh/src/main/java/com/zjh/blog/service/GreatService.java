package com.zjh.blog.service;

import com.zjh.blog.domain.Great;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther：zjh
 * @Description：点赞接口
 * @Data：2020/2/27 15:50
 * Version 1.0
 */
public interface GreatService {

    /**
     * 新增图片
     * @param：Picture
     * @return
     */
    public Integer saveGreat(Great great);

    /**
     * 删除图片
     * @param id
     * @return
     */
    public Integer delGreat(Integer id);

    /**
     * 查看是否有imageId------>用户ip的关系
     * @param：userIp
     * @param：imageId
     * @return
     */
    public Great isClick(@Param("userIp") String userIp,
                         @Param("imageId") Integer imageId);
    /**
     * 根据imageid 删除所有对应的记录
     * @param：id
     * @return
     */
    public Integer deleteByImageId(Integer id);
}
