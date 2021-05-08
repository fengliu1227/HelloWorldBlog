package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAll();
    List<Blog> getByUserId(Integer userId);
    Blog getBlogById(Integer id);
    void insertBlog(Blog blog);
    Integer getIdByAllOtherInfo(Blog blog);
    void updateBlog(Integer id, Blog blog);
    void deleteById(Integer id);
}
