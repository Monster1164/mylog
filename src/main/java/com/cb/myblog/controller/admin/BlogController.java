package com.cb.myblog.controller.admin;


import com.cb.myblog.pojo.Blog;
import com.cb.myblog.pojo.User;
import com.cb.myblog.service.BlogService;
import com.cb.myblog.service.TagService;
import com.cb.myblog.service.TypeService;
import com.cb.myblog.vo.BlogQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class  BlogController {

    private  final String INPUT = "admin/blogs-input";
    private  final String LIST = "admin/blogs";
    private  final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private TagService tagService = null;
    @Autowired
    private BlogService blogService = null;
    @Autowired
    TypeService typeService =null;


    /**
     * 博客列表
     * 获得所有博客文章或者根据blogQuery条件查询一部分
     * @param model
     * @param pageNum
     * @param blogQuery
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNum,
                        BlogQuery blogQuery){


//        System.out.println(blogQuery);
        //获取所有分类z
        model.addAttribute("types",typeService.listType());
        //分页
        PageHelper.startPage(pageNum,3);
        List<Blog> blogList = blogService.listBlog(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        model.addAttribute("pageInfo",pageInfo);

        return LIST;
    }

    /**
     *  ajax动态刷新一部分
     * @param model
     * @param pageNum
     * @param blog
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer pageNum,
                        BlogQuery  blog){

//        System.out.println(pageNum);
        PageHelper.startPage(pageNum,3);
        List<Blog> blogList = blogService.listBlog(blog);

        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/blogs :: blogList";
    }

    /**
     * 获得所有标签和分页
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){

        //初始化 标签和分页
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());

        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listType());
    }

    /**
     * 编辑修改博客
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(Model model, @PathVariable Long id){

        //初始化 标签和分页
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog",blog);

        return INPUT;
    }

    /**
     * 新增博客
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog , RedirectAttributes attributes , HttpSession session){

//        System.out.println("---------------");
//        System.out.println(blog.getType().getId());

//        System.out.println(blog);
        User user = (User)session.getAttribute("user");

        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));


        blog.setUser(user);
//        if (blog.getId() == null) {   //id为空，则为新增
//            blogService.saveBlog(blog);
//        } else {
//            blog = blogService.updateBlog(blog.getId(),blog);
//        }

        //id为空，则为新增
        Blog blog1 = blogService.saveBlog(blog);

        if (blog==null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }

        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

}
