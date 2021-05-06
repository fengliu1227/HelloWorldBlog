package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.Comment;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.CommentService;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;

    @RequestMapping(value="/user", method= RequestMethod.GET)
    public String userProfile(Model model, HttpSession httpSession){
        Integer userId = null;
        if(httpSession.getAttribute("userId") != null){
            userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
        }else {
            String userName = ((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUsername();
            List<UserInfo> list = userInfoService.getByUsername(userName);
            userId = list.get(0).getId();
            httpSession.setAttribute("userId", userId);
        }
        UserInfo userInfo = userInfoService.getById(userId);
        List<Comment> comments = commentService.getByUserId(userId);
        List<Blog> blogs = blogService.getByUserId(userId);
        model.addAttribute("blogs", blogs);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("comments", comments);
        return "profile";
    }
}
