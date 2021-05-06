package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getByUserId(Integer userId);
}
