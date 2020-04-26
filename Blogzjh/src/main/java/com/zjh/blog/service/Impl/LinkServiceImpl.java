package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.CommentMapper;
import com.zjh.blog.dao.LinkMapper;
import com.zjh.blog.domain.Link;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2019/12/5 15:32
 * Version 1.0
 */
@Service("LinkService")
public abstract class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> getTotalData() {
        return linkMapper.getTotalData();
    }

    @Override
    public PageBean<Link> listByPage(PageBean<Link> pageBean) {
        pageBean.setResult(linkMapper.listByPage(pageBean.getStart(),pageBean.getEnd()));
        pageBean.setTotal(linkMapper.getTotalCount());
        return pageBean;
    }

    @Override
    public Long getTotalCount() {
        return linkMapper.getTotalCount();
    }

    @Override
    public Integer addLink(Link link) {
        return linkMapper.addLink(link);
    }

    @Override
    public Integer updateLink(Link link) {
        return linkMapper.updateLink(link);
    }

    @Override
    public Integer deleteLink(Integer id) {
        return linkMapper.deleteLink(id);
    }

}
