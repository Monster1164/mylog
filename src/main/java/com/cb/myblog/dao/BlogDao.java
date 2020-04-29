package com.cb.myblog.dao;

import com.cb.myblog.pojo.Blog;
import com.cb.myblog.vo.BlogQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    Blog getBlog(Long id);

    List<Blog> listBlog(BlogQuery blog);

    List<Blog> listBlogByTagid(Long tagId);

    List<Blog> getAllBlog();

    Long saveBlog(Blog blog);

    Long updateBlog(Blog blog);

    //根据推荐查询
    List<Blog> listRecommendBlog();

    void deleteBlog(Long id);

    //根据搜索得到的文本查找
    List<Blog> listBlogByText(String query);

    //更新views字段
    void updateViews(Long id );

    // 时间查询
    List<String> listYears();

    //根据年查询博客
    List<Blog> listBlogByYear(String year);

    //计算所有条目
    int ConutBlog();
}
