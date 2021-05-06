package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.Blog;

import java.util.Collection;

public interface BlogService {
    Collection<Blog> getAll();
    Blog getBlogById(Integer id);
    void insertBlog(Blog blog);
}
