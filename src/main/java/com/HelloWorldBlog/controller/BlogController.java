package com.HelloWorldBlog.controller;


import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserInfoService userInfoService;

    //home page
    @RequestMapping("/blog")
    public String getBlogs(Model model){
        blogService.getAll();
        Collection<Blog> blogs = blogService.getAll();
        model.addAttribute("blogs", blogs);
        return "blog";
    }

    @RequestMapping(value="/blog/{id}", method= RequestMethod.GET)
    public String getBlogById(@PathVariable("id") Integer id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "detail";
    }
}
