package com.HelloWorldBlog.test;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.User;
import com.HelloWorldBlog.bean.UserExample;
import com.HelloWorldBlog.dao.UserMapper;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class mybatisTest {

    @Autowired
    BlogService blogService;
    UserService userService;

    @Test
    public void test() {
        System.out.println(blogService.getAll());
    }
}
