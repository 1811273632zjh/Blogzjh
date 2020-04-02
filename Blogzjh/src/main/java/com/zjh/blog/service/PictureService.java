package com.zjh.blog.service;

import com.zjh.blog.domain.PageBean;
import com.zjh.blog.domain.Picture;

import java.util.List;

/**
 * @Auther：zjh
 * @Description：相册图片 接口
 * @Data：2020/2/26 17:55
 * Version 1.0
 */
public interface PictureService {
    /**
     * 查询所有图片信息，按照点赞排行
     * @return
     */
	List<Picture> getTotalData();

	/**
	  * @Description: 查询总记录数
	  * @return:
	  */
	Long getTotalCount();

    /**
     * @Description: 添加图片
     * @return:
     */
	Integer addPicture(Picture picture);

    /**
     * @Description: 更新图片
     * @return:
     */
	Integer updatePicture(Picture picture);

	Integer deletePicture(Integer id);

    /**
     * @Description: 分页
     * @return:
     */
	PageBean<Picture> listByPage(PageBean<Picture> pageBean);

	/**
	  * @Description: 根据id获取图片
	  * @return:
	  */
	Picture getPictureByid(Integer id);
}
