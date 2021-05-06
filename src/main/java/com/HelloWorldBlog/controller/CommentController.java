package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.Comment;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.CommentService;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value="/comment", method= RequestMethod.POST)
    public Comment addComment(@RequestBody Comment comment, HttpSession httpSession) throws ParseException {
        if(httpSession.getAttribute("userId") != null && httpSession.getAttribute("userName") != null){
            Integer userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
            String userName = httpSession.getAttribute("userName").toString();
            comment.setUserName(userName);
            comment.setUserId(userId);
        }else{
            String userName = ((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUsername();
            List<UserInfo> list = userInfoService.getByUsername(userName);
            Integer userId = list.get(0).getId();
            comment.setUserName(userName);
            comment.setUserId(userId);
            httpSession.setAttribute("userId", userId);
            httpSession.setAttribute("userName", userName);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date(System.currentTimeMillis()));
        Date createDate = formatter.parse(now);
        comment.setPostTime(createDate);
        commentService.insert(comment);
        return comment;
    }
}
