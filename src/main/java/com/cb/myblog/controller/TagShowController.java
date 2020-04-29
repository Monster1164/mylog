package com.cb.myblog.controller;


import com.cb.myblog.pojo.Blog;
import com.cb.myblog.pojo.Tag;
import com.cb.myblog.pojo.Type;
import com.cb.myblog.service.BlogService;
import com.cb.myblog.service.TagService;
import com.cb.myblog.service.TypeService;
import com.cb.myblog.vo.BlogQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TagShowController {

    @Autowired
    private TagService tagService = null;
    @Autowired
    private BlogService blogService = null;

    @GetMapping("/tags/{id}")
    public String tags(Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNum,
                        @PathVariable Long id){

        //返回所有的标签
        List<Tag> tags = tagService.listType();

        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);

        PageHelper.startPage(pageNum,2);
        List<Blog> blogs = blogService.listBlogByTagid(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTagId",id);

        return "tags";
    }
}
