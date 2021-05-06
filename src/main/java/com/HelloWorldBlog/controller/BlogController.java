package com.HelloWorldBlog.controller;


import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

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

    @RequestMapping("/addBlog")
    public String addBlog(Blog blog, HttpSession httpSession){
        if(httpSession.getAttribute("userId") != null && httpSession.getAttribute("userName") != null){
            Integer userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
            String userName = httpSession.getAttribute("userName").toString();
            blog.setUserName(userName);
            blog.setUserId(userId);
        }else{
            String userName = ((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUsername();
            blog.setUserName(userName);
            List<UserInfo> list = userInfoService.getByUsername(userName);
            Integer userId = list.get(0).getId();
            blog.setUserName(userName);
            blog.setUserId(userId);
            httpSession.setAttribute("userId", userId);
            httpSession.setAttribute("userName", userName);
        }
        blogService.insertBlog(blog);
        return "redirect:/blog";
    }
}
