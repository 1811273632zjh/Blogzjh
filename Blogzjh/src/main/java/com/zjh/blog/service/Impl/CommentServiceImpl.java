package com.zjh.blog.service.Impl;

import com.zjh.blog.dao.CommentMapper;
import com.zjh.blog.domain.Comment;
import com.zjh.blog.domain.PageBean;
import com.zjh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：实现评论service接口
 * @Data：2020/4/2 16:17
 * Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageBean<Comment> listByPage(PageBean<Comment> pageBean) {
        pageBean.getMap().put("start", pageBean.getStart());
        pageBean.getMap().put("end", pageBean.getEnd());
        pageBean.setResult(commentMapper.listByPage(pageBean.getMap()));
        pageBean.setTotal(commentMapper.getTotal(pageBean.getMap()));
        return pageBean;
    }

    @Override
    public Long getTotao(Map<String, Object> map) {
        return commentMapper.getTotal(map);
    }

    @Override
    public Comment getById(Integer id) {
        return commentMapper.getById(id);
    }

    @Override
    public Integer saveComment(Comment comment) {
        return commentMapper.saveComment(comment);
    }

    @Override
    public Integer deleteComment(Integer id) {
        return commentMapper.deleteComment(id);
    }

    @Override
    public Integer updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    public Long deleteCommentByBlogId(Integer blogId) {
        return commentMapper.deleteCommentByBlogId(blogId);
    }

    @Override
    public List<Comment> getCommentData(Map<String, Object> map) {
        return commentMapper.listByPage(map);
    }

    @Override
    public List<Comment> queryCommentsByBlogId(Integer blogId) {
        return commentMapper.queryByBlogId(blogId);
    }

    @Override
    public List<Comment> listComment(Map<String, Object> map) {
        return commentMapper.listByPage(map);
    }
}
