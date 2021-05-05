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

        int a=5;
        int b=10;
        swap(a,b);
        System.out.println(a +" " + b);
    }
    static void swap(int a, int b){
        int temp = a;
        a= b;
        b=a;
    }
}
