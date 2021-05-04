package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.User;
import com.HelloWorldBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SystemController {

    @Autowired
    UserService userService;

    @RequestMapping(value="login", method= RequestMethod.POST)
    public String login(User user){
        System.out.println(user);
        return "blog";
    }

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String addNewUser(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
    @RequestMapping(value="register", method= RequestMethod.POST)
    public String register(User user){
        System.out.println(user);
        userService.addUser(user);
        return "blog";
    }


}



