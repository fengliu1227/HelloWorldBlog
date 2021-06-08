package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping(value="/admin")
    public String admin(){
        return "adminMain";
    }

    @RequestMapping(value="/admin/admin/{id}", method = RequestMethod.POST)
    public String updateAdmin(@PathVariable("id")Integer id){
        UserInfo userInfo = userInfoService.getById(id);
        if(!userInfo.getRole().equals("ADMIN")){
            userInfo.setRole("ADMIN");
            userInfoService.changeRole(userInfo, id);
        }
        return "redirect:/user/" + id;
    }

    @RequestMapping(value="/admin/user/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id")Integer id){
        UserInfo userInfo = userInfoService.getById(id);
        if(!userInfo.getRole().equals("USER")){
            userInfo.setRole("USER");
            userInfoService.changeRole(userInfo, id);
        }
        return "redirect:/user/" + id;
    }

    @RequestMapping(value="/admin/banned/{id}", method = RequestMethod.POST)
    public String BanUser(@PathVariable("id")Integer id){
        UserInfo userInfo = userInfoService.getById(id);
        if(!userInfo.getRole().equals("BANNED")){
            userInfo.setRole("BANNED");
            userInfoService.changeRole(userInfo, id);
        }
        return "redirect:/user/" + id;
    }
}
