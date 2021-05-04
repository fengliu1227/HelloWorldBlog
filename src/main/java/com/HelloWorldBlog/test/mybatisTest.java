package com.HelloWorldBlog.test;

import com.HelloWorldBlog.service.imp.BlogServiceImpl;
import com.HelloWorldBlog.service.imp.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class mybatisTest {

    @Autowired
    BlogServiceImpl blogService;
    UserServiceImpl userService;

    @Test
    public void test() {
        System.out.println(blogService.getAll());
    }
}
