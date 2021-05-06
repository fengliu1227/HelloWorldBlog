package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInfoService extends UserDetailsService {
    UserInfo getById(Integer id);
    void addUser(UserInfo userInfo);
    List<UserInfo> getByEmail(String Email);
    public List<UserInfo> getByUsername(String Email);
}
