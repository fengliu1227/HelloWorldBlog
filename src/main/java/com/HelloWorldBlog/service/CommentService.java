package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getByUserId(Integer userId);
    List<Comment> getByBlogId(Integer blogId);
    Comment getById(Integer id);
    void insert(Comment comment);
    void deleteById(Integer id);
}
