package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.User;

import java.util.List;

public interface UserService {
    User getById(Integer id);
    void addUser(User user);
    List<User> getByEmail(String Email);
}
