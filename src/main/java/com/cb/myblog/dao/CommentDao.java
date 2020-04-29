package com.cb.myblog.dao;

import com.cb.myblog.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    List<Comment> listCommentByBlogId(Long blogId);

    void  saveComment(Comment comment);

    Comment selectCommnetById(Long id);

    Comment selectCommentByPId(Long Pid);
}
