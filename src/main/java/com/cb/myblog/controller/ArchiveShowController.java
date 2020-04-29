package com.cb.myblog.controller;


import com.cb.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService ;

    @GetMapping("/archives")
    public String archives(Model model){

        model.addAttribute("archiveMap",blogService.listBlogByYear());
        model.addAttribute("blogCount",blogService.ConutBlog());
        return "archives";
    }
}
