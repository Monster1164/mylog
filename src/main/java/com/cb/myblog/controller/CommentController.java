package com.cb.myblog.controller;


import com.cb.myblog.pojo.Comment;
import com.cb.myblog.pojo.User;
import com.cb.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService =null;

    @Value("${comment.avator}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String commnets(@PathVariable Long blogId , Model model){

        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String commnets(Comment comment, HttpSession session){

        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }

        Long id = comment.getBlog().getId();
        comment.setBlogId(id);

        commentService.saveComment(comment);
        return "redirect:/comments/" +  id;
    }
}
