package com.HelloWorldBlog.service;

import com.HelloWorldBlog.bean.User;
import com.HelloWorldBlog.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserService() { ;
    }

    public User getById(Integer id){
        System.out.println("haha ");

//        return userMapper.selectByPrimaryKey(id);
        return null;
    }
}
