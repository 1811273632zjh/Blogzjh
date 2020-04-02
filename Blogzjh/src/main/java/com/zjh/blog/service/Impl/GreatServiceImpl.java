package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.GreatMapper;
import com.zjh.blog.domain.Great;
import com.zjh.blog.service.GreatService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther：zjh
 * @Description：点赞service实现
 * @Data：2020/2/27 15:52
 * Version 1.0
 */
@Service
public class GreatServiceImpl implements GreatService {

    @Autowired
    private GreatMapper greatMapper;

    @Override
    public Integer saveGreat(Great great) {
        return greatMapper.addGreat(great);
    }

    @Override
    public Integer delGreat(Integer id) {
        return greatMapper.deleteGreat(id);
    }

    @Override
    public Great isClick(@Param("userIp") String userIp,
                         @Param("imageId") Integer imageId) {
        return greatMapper.getByUserIpAndImageId(userIp, imageId);
    }

    @Override
    public Integer deleteByImageId(Integer id) {
        return greatMapper.deleteGreatByImageId(id);
    }
}
