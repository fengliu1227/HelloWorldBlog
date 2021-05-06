package com.HelloWorldBlog.test;

import com.HelloWorldBlog.service.imp.BlogServiceImpl;
import com.HelloWorldBlog.service.imp.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mybatisTest {

    @Autowired
    BlogServiceImpl blogService;
    UserServiceImpl userService;

    @Test
    public void test() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
    }
}
