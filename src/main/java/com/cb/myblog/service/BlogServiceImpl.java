package com.cb.myblog.service;

import com.cb.myblog.dao.BlogDao;
import com.cb.myblog.dao.TagDao;
import com.cb.myblog.dao.TypeDao;
import com.cb.myblog.dao.UserDao;
import com.cb.myblog.myException.NotFoundException;
import com.cb.myblog.pojo.Blog;
import com.cb.myblog.pojo.Comment;
import com.cb.myblog.pojo.Tag;
import com.cb.myblog.pojo.User;
import com.cb.myblog.util.MarkdownUtils;
import com.cb.myblog.util.MyBeanUtils;
import com.cb.myblog.util.TagidsStringToList;
import com.cb.myblog.vo.BlogQuery;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    BlogDao blogDao = null;

    @Autowired
    UserDao userDao =null;

    @Autowired
    TypeDao typeDao = null;
    @Autowired
    TagDao tagDao = null;



    @Override
    public Blog getAndConvert(Long id) {

        Blog blog = blogDao.getBlog(id);

        //把tags列表获取
        List<Long> tagids = TagidsStringToList.convertToList(blog.getTagIds());
        ArrayList<Tag> list = new ArrayList<>();

        for (Long long1 : tagids) {

            list.add(tagDao.getType(long1));
        }
        blog.setTags(list);

        User user = userDao.getzUser();
        blog.setUser(user);

        if(blog==null){
            throw  new NotFoundException("该博客已经删除");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));

        blogDao.updateViews(id);
        return blog;
    }

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public List<Blog> listBlog(BlogQuery blog) {

        List<Blog> blogs = blogDao.listBlog(blog);
        User user = userDao.getzUser();

        for (Blog blog1:blogs){
            blog1.setUser(user);
        }
        return blogs;
    }

    @Override
    public List<Blog> listBlogByTagid(Long tagId) {

        List<Blog> blogs = blogDao.listBlogByTagid(tagId);


        //把tags列表获取
        for(Blog blog : blogs){

            List<Long> tagids = TagidsStringToList.convertToList(blog.getTagIds());
            ArrayList<Tag> list = new ArrayList<>();

            for (Long long1 : tagids) {

                list.add(tagDao.getType(long1));
            }
            blog.setTags(list);
        }




        User user = userDao.getzUser();
        for (Blog blog1:blogs){
            blog1.setUser(user);
        }
        return blogs;
    }

    @Override
    public List<Blog> getAllBlog(int pageNum) {
        User user = userDao.getzUser();

        PageHelper.startPage(pageNum,3);
        List<Blog> allBlog = blogDao.getAllBlog();
        for (Blog blog:allBlog){
            blog.setUser(user);
        }
        return allBlog;
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogDao.saveBlog(blog);
        }else {
            blog.setUpdateTime(new Date());
            blog = updateBlog(blog.getId(),blog);
        }

        return blog;
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {

        Blog blog1 = blogDao.getBlog(id);
        if(blog1 == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,blog1, MyBeanUtils.getNullPropertyNames(blog));

        blogDao.updateBlog(blog1);
        return blog1;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> listRecommendBlog() {
        return blogDao.listRecommendBlog();
    }

    @Override
    public List<Blog> listBlogByText(String query) {

        List<Blog> blogs = blogDao.listBlogByText(query);

        User user = userDao.getzUser();

        for (Blog blog:blogs){
            blog.setUser(user);
        }

        return blogs;
    }

    @Override
    public List<String> listYears() {
        return blogDao.listYears();
    }

    @Override
    public Map<String , List<Blog>> listBlogByYear() {

        List<String> years = listYears();
        Map<String , List<Blog>> map = new LinkedHashMap<>();
        for (String s : years){
            List<Blog> blogs = blogDao.listBlogByYear(s);
            map.put(s,blogs);
        }

        return map;
    }

    @Override
    public int ConutBlog() {
        return blogDao.ConutBlog();
    }


}
