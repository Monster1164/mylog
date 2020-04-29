package com.cb.myblog;

import com.cb.myblog.dao.BlogDao;
import com.cb.myblog.dao.CommentDao;
import com.cb.myblog.dao.UserDao;
import com.cb.myblog.pojo.Blog;
import com.cb.myblog.pojo.Comment;
import com.cb.myblog.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MylogApplicationTests {

    @Autowired
    UserDao userDao = null;

    @Autowired
    CommentDao commentDao =null;

    @Autowired
    BlogDao blogDao = null;

    //测试commentDao插入
    @Test
    public void testBlogs(){
//        Comment comment = new Comment();
//        comment.setAdminComment(true);
//        comment.setBlogId(5l);
//        comment.setParentCommentId(3l);
//        comment.setContent("ccccc");
//        commentDao.saveComment(comment);
//
//        Comment comment1 = new Comment();
//        comment1.setContent("bbbbb");
//        comment1.setAdminComment(true);
//        comment1.setBlogId(5l);
//        comment1.setParentCommentId(3l);
//        commentDao.saveComment(comment1);
    }

    //测试查找commentList
    @Test
    public void testGetCommentList(){

        List<Comment> comments = commentDao.listCommentByBlogId(5l);

        System.out.println(comments);
    }
    //测试时间查询
    @Test
    public void testTimeCommentList(){

//        List<Comment> comments = commentDao.listCommentByBlogId(5l);

//        System.out.println(comments);
        List<String> strings = blogDao.listYears();
        System.out.println(strings);
        Map<String , List<Blog>> map = new LinkedHashMap<>();
        for (String s : strings){
            List<Blog> blogs = blogDao.listBlogByYear(s);
            map.put(s,blogs);
        }
        System.out.println(map);
    }





}
