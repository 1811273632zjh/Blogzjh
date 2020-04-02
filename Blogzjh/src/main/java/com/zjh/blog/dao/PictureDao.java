package com.zjh.blog.dao;

import com.zjh.blog.domain.Picture;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/2/27 11:14
 * Version 1.0
 */
@Repository
public interface PictureDao {
    /**
     * 查询所有图片信息，按照点赞排行
     * @return:
     */
	List<Picture> getTotalData();

    /**
     * 分页查询
     * @param:pageBean
     * @return:
     */

    List<Picture> listByPage(Map<String, Object> map);

    /**
     * 查询总记录数
     * @return
     */
    Long getTotalCount();

    /**
     * 新增图片
     * @param:Picture
     * @return
     */
    Integer addPicture(Picture picture);

    /**
     * 删除图片
     * @param:id
     * @return:
     */
    Integer deletePicture(Integer id);

    /**
     * 更新图片
     * @param:Picture
     * @return:
     */
    Integer updatePicture(Picture picture);

    /**
     * 根据id查询图片
     * @param:id
     * @return:
     */
    Picture getPictureById(Integer id);
}
