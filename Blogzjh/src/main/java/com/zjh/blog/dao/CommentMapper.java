package com.zjh.blog.dao;

import com.zjh.blog.domain.Comment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：评论dao类
 * @Data：2019/12/11 15:02
 * Version 1.0
 */
@Repository
public interface CommentMapper {
    /**
     * 分页查询评论信息
     * @param map
     * @return
     */
    List<Comment> listByPage(Map<String, Object> map);

    /**
     * 获取总记录数目
     *
     * @param map
     * @return
     */
    Long getTotal(Map<String, Object> map);

    /**
     * 根据id查询评论信息
     *
     * @param id
     * @return
     */
    Comment getById(Integer id);

    /**
     * 添加评论信息
     *
     * @param comment
     * @return
     */
    Integer saveComment(Comment comment);

    /**
     * 根据id删除评论
     *
     * @param id
     * @return
     */
    Integer deleteComment(Integer id);

    /**
     * 更改评论审核状态
     *
     * @param comment
     * @return
     */
    Integer updateComment(Comment comment);

    /**
     * 根据博客id查询评论信息
     *
     * @param blogId
     * @return
     */
    List<Comment> queryByBlogId(Integer blogId);

    /**
     * 删除评论信息通过博客id
     *
     * @param blogId
     * @return
     */
    Long deleteCommentByBlogId(Integer blogId);
}
