package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.BlogExample;
import com.HelloWorldBlog.dao.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public Collection<Blog> getAll() {
        return blogMapper.selectByExample(new BlogExample());
    }
}
