package com.zjh.blog.service;

import com.zjh.blog.domain.Comment;
import com.zjh.blog.domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：评论Service接口
 * @Data：2020/4/2 15:50
 * Version 1.0
 */
public interface CommentService {

    /**
      * @Description: 分页查询评论信息
      * @Param: pageBean
      * @return:
      */
    PageBean<Comment> listByPage(PageBean<Comment> pageBean);

    /**
      * @Description: 获取总记录数
      * @Param: ，map
      * @return:
      */
    Long getTotao(Map<String, Object> map);

    /**
      * @Description: 根据id查询评论信息
      * @Param: id
      * @return:
      */
    Comment getById(Integer id);

    /**
      * @Description: 添加评论信息
      * @Param: commenet
      * @return:
      */
    Integer saveComment(Comment comment);

    /**
      * @Description: 根据id删除评论
      * @Param: commment
      * @return:
      */
    Integer deleteComment(Integer id);

    /**
      * @Description: 修改评论审核状态
      * @Param: comment
      * @return:
      */
    Integer updateComment(Comment comment);

    /**
      * @Description: 删除评论信息通过博客id
      * @Param: blogId
      * @return:
      */
    Long deleteCommentByBlogId(Integer blogId);

    /**
      * @Description: 查询所有评论消息----仅仅这样直接输出不行。既需要分页，也需要条件查询
      * @Param: map
      * @return: 
      */
    @Deprecated
    List<Comment> getCommentData(Map<String, Object> map);

    /**
      * @Description: 根据blogid查询所有评论 state= 1
      * @Param: blogId
      * @return: 
      */
    List<Comment> queryCommentsByBlogId(Integer blogId);

    /**
      * @Description: 复杂查询
      * @Param: map
      * @return: 
      */
    List<Comment> listComment(Map<String, Object> map);

}
