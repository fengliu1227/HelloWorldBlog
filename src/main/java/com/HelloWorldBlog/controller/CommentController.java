package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.Comment;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.CommentService;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        Integer id = commentService.getIdByAllOtherInfo(comment);
        comment.setId(id);
        return comment;
    }

    @ResponseBody
    @RequestMapping(value="/comment/{id}", method= RequestMethod.DELETE)
    public Integer addComment(@PathVariable("id") Integer id) {
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        Comment comment = commentService.getById(id);
        if(comment.getUserName().equals(userName)){
            commentService.deleteById(id);
            return id;
        }else{
            return null;
        }
    }

    @RequestMapping(value="/comment/{id}", method= RequestMethod.PUT)
    public String updateComment(@PathVariable("id") Integer id, Comment comment) {
        Integer blogId = commentService.getById(id).getBlogId();
        commentService.updateComment(id, comment);
        return "redirect:/blog/"+blogId;
    }
}
