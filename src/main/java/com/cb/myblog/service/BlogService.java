package com.cb.myblog.service;

import com.cb.myblog.pojo.Blog;
import com.cb.myblog.vo.BlogQuery;

import java.util.List;
import java.util.Map;

public interface BlogService {




    //markdown 转 html
    Blog getAndConvert(Long id);

    Blog getBlog(Long id);

    List<Blog> listBlog(BlogQuery blog);

    List<Blog> listBlogByTagid(Long tagId);

    List<Blog> getAllBlog(int pageNum);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id ,Blog blog);

    void deleteBlog(Long id);

    List<Blog> listRecommendBlog();

    List<Blog> listBlogByText(String query);

    // 时间查询
    List<String> listYears();

    //根据年查询博客
    Map<String , List<Blog>> listBlogByYear();

    //计算所有条目
    int ConutBlog();
}
