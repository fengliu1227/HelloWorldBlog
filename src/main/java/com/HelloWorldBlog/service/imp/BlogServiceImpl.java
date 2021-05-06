package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.BlogExample;
import com.HelloWorldBlog.dao.BlogMapper;
import com.HelloWorldBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public Collection<Blog> getAll() {
        BlogExample blogExample = new BlogExample();
        blogExample.setOrderByClause("id desc");
        return blogMapper.selectByExample(blogExample);
    }


    public Blog getBlogById(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    public void insertBlog(Blog blog){
        blogMapper.insert(blog);
    }
}
