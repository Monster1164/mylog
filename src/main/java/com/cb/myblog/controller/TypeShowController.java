package com.cb.myblog.controller;


import com.cb.myblog.pojo.Blog;
import com.cb.myblog.pojo.Type;
import com.cb.myblog.service.BlogService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService = null;
    @Autowired
    private BlogService blogService = null;

    @GetMapping("/types/{id}")
    public String types(Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNum,
                        @PathVariable Long id){

        //返回所有的分类
        List<Type> types = typeService.listType();

        if (id == -1){
            id = types.get(0).getId();
        }

        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        model.addAttribute("types",types);

        PageHelper.startPage(pageNum,2);
        List<Blog> blogs = blogService.listBlog(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);


        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTypeId",id);

        return "types";
    }
}
