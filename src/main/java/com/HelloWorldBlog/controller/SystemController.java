package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.User;
import com.HelloWorldBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SystemController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value="login", method= RequestMethod.POST)
    public String login(User user){
        List<User> users = userService.getByEmail(user.getEmail());
        System.out.println(users);
        if(users.size() == 1){
            boolean matches = passwordEncoder.matches(user.getPassword(), users.get(0).getPassword());
            if(matches){
                return "redirect:/blog";
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String addNewUser(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
    @RequestMapping(value="register", method= RequestMethod.POST)
    public String register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/blog";
    }
}




