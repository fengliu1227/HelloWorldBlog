package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.Comment;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.CommentService;
import com.HelloWorldBlog.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public Integer deleteComment(@PathVariable("id") Integer id) {
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        UserInfo userInfo = list.get(0);
        Comment comment = commentService.getById(id);
        if(comment.getUserName().equals(userName) || userInfo.getRole().equals("ADMIN")){
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

    @RequestMapping(value="/comment/user/{id}", method= RequestMethod.GET)
    public String getCommentFromUser(@RequestParam(value="pn",defaultValue="1")Integer pn, @PathVariable("id") Integer id, Model model) {
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        PageHelper.startPage(pn, 5);
        List<Comment> comments = commentService.getByUserId(id);
        PageInfo pageInfo = new PageInfo(comments, 5);
        model.addAttribute("comments", comments);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("userId", id);
        if(userId.equals(id)){
            model.addAttribute("mine", true);
        }else{
            model.addAttribute("mine", false);
        }
        return "commentResult";
    }

    @RequestMapping(value="/comment/search/{id}", method= RequestMethod.GET)
    public String search(@RequestParam(value="pn",defaultValue="1")Integer pn, @PathVariable("id") Integer id, @RequestParam(value="keyword")String keyword, Model model) {
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        PageHelper.startPage(pn, 5);
        List<Comment> comments = commentService.search(keyword, id);
        PageInfo pageInfo = new PageInfo(comments, 5);
        model.addAttribute("comments", comments);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("userId", id);
        if(userId.equals(id)){
            model.addAttribute("mine", true);
        }else{
            model.addAttribute("mine", false);
        }
        return "commentResult";
    }

    @RequestMapping(value="/comment/search/admin", method= RequestMethod.GET)
    public String searchByAdmin(@RequestParam(value="pn",defaultValue="1")Integer pn,  @RequestParam(value="keyword")String keyword, Model model) {
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        PageHelper.startPage(pn, 5);
        List<Comment> comments = commentService.search(keyword);
        PageInfo pageInfo = new PageInfo(comments, 5);
        model.addAttribute("comments", comments);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("userInfo", list.get(0));
        return "commentResult";
    }
}
