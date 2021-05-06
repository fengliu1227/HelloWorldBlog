package com.HelloWorldBlog.controller;


import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserInfoService userInfoService;

    //home page
    @RequestMapping("/blog")
    public String getBlogs(@RequestParam(value="pn",defaultValue="1")Integer pn, Model model, HttpSession httpSession){
        PageHelper.startPage(pn, 6);
        Collection<Blog> blogs = blogService.getAll();
        PageInfo pageInfo = new PageInfo((List) blogs, 5);
//      PageInfo contains a very comprehensive paging property
//        assertEquals(1, page.getPageNum());
//        assertEquals(10, page.getPageSize());
//        assertEquals(1, page.getStartRow());
//        assertEquals(10, page.getEndRow());
//        assertEquals(183, page.getTotal());
//        assertEquals(19, page.getPages());
//        assertEquals(1, page.getFirstPage());
//        assertEquals(8, page.getLastPage());
//        assertEquals(true, page.isFirstPage());
//        assertEquals(false, page.isLastPage());
//        assertEquals(false, page.isHasPreviousPage());
//        assertEquals(true, page.isHasNextPage());
        model.addAttribute("blogs", blogs);
        model.addAttribute("pageInfo", pageInfo);
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        httpSession.setAttribute("userId", userId);
        httpSession.setAttribute("userName", userName);
        return "blog";
    }

    @RequestMapping(value="/blog/{id}", method= RequestMethod.GET)
    public String getBlogById(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDateStr = formatter.format(blog.getCreateTime());
        model.addAttribute("createDateStr", createDateStr);
        return "detail";
    }

    //put the data in the response body, if it is an object, put it in JSON
    @ResponseBody
    @RequestMapping(value="/addBlog", method=RequestMethod.POST)
    public Blog addBlog(@RequestBody Blog blog, HttpSession httpSession) throws ParseException {
        System.out.println("blog 方法中   " + blog);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date(System.currentTimeMillis()));
        Date createDate = formatter.parse(now);
        blog.setCreateTime(createDate);
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
            List<UserInfo> list = userInfoService.getByUsername(userName);
            Integer userId = list.get(0).getId();
            blog.setUserName(userName);
            blog.setUserId(userId);
            httpSession.setAttribute("userId", userId);
            httpSession.setAttribute("userName", userName);
        }
        blogService.insertBlog(blog);
        return blog;
    }
}
