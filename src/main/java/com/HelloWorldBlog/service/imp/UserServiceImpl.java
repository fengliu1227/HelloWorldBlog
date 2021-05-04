package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.User;
import com.HelloWorldBlog.dao.UserMapper;
import com.HelloWorldBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public User getById(Integer id){
        System.out.println("haha ");

//        return userMapper.selectByPrimaryKey(id);
        return null;
    }
}
