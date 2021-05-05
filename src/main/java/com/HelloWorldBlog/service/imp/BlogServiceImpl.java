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
        return blogMapper.selectByExample(new BlogExample());
    }


    public Blog getBlogById(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }
}
