package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.PictureDao;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.domain.Picture;
import com.zjh.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/2/27 11:13
 * Version 1.0
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public List<Picture> getTotalData() {
        return pictureDao.getTotalData();
    }

    @Override
    public Long getTotalCount() {
        return pictureDao.getTotalCount();
    }

    @Override
    public Integer addPicture(Picture picture) {
        return pictureDao.addPicture(picture);
    }

    @Override
    public Integer updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    @Override
    public Integer deletePicture(Integer id) {
        return pictureDao.deletePicture(id);
    }

    @Override
    public PageBean<Picture> listByPage(PageBean<Picture> pageBean) {
        pageBean.getMap().put("start",pageBean.getStart());
        pageBean.getMap().put("end",pageBean.getEnd());
        pageBean.setResult(pictureDao.listByPage(pageBean.getMap()));
        pageBean.setTotal(pictureDao.getTotalCount());
        return pageBean;
    }

    @Override
    public Picture getPictureByid(Integer id) {
        return pictureDao.getPictureById(id);
    }
}
