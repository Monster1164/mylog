package com.cb.myblog.controller;

import com.cb.myblog.dao.BlogDao;
import com.cb.myblog.pojo.Blog;
import com.cb.myblog.pojo.Tag;
import com.cb.myblog.pojo.Type;
import com.cb.myblog.service.BlogService;
import com.cb.myblog.service.TagService;
import com.cb.myblog.service.TypeService;
import com.cb.myblog.vo.BlogQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private BlogService blogService = null;

    @Autowired
    private TypeService typeService = null;

    @Autowired
    private TagService tagService = null;
    /**
     * 显示博客首页的所有文章
     * @return
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNum
                        ){

        //分页查询博客
        PageHelper.startPage(pageNum,3);
        List<Blog> blogList = blogService.getAllBlog(pageNum);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("page",pageInfo);

        //查询出所有分类和标签
//        PageHelper.startPage(pageNum,3);
        PageInfo<Type> types = new PageInfo<>(typeService.listType());

//        PageHelper.startPage(pageNum,3);
        PageInfo<Tag> tags = new PageInfo<>(tagService.listType());
        PageInfo<Blog> recommendBlogs = new PageInfo<>(blogService.listRecommendBlog());

        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("recommendBlogs",recommendBlogs);

        return "index";
    }


    @PostMapping("/search")
    public String search(@RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNum,
                         Model model,@RequestParam String query){

        PageHelper.startPage(pageNum,3);
        List<Blog> blogs = blogService.listBlogByText(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("page",pageInfo);
        model.addAttribute("query",query);

        return "search";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id , Model model){

        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog",blog);

        return "blog";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){

        List<Blog> allBlog = blogService.getAllBlog(1);

        model.addAttribute("newblogs",allBlog);
        return "_fragments :: newblogList";
    }



}
