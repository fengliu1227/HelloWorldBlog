package com.HelloWorldBlog.controller;


import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class blogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    //home page
    @RequestMapping("/blog")
    public String getBlogs(Model model){
        blogService.getAll();
        Collection<Blog> blogs = blogService.getAll();
        model.addAttribute("blogs", blogs);
        return "blog";
    }
}
